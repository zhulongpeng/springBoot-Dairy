package com.zlp.dairy.business.controller;


import com.alibaba.fastjson.JSON;
import com.zlp.dairy.base.util.ResResult;
import com.zlp.dairy.base.util.XaUtil;
import com.zlp.dairy.business.entity.Country;
import com.zlp.dairy.business.entity.Language;
import com.zlp.dairy.business.repository.CountryRepository;
import com.zlp.dairy.business.service.CountryService;
import com.zlp.dairy.business.service.LanguageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Api(value = "country")
@RestController
public class CountryController {

    @Autowired
    private LanguageService languageService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryRepository countryRepository;

    @ApiOperation("通过Excel导入国家信息")
    @RequestMapping("/v1/cms/countries/import")
    @ResponseBody
    public ResResult<String> importCountry(
            @RequestParam(value = "file") MultipartFile xlsFile
    ) {
        ResResult<String> result = new ResResult<>();
        if (XaUtil.isEmpty(xlsFile.getOriginalFilename())) return null;
        String ext = xlsFile.getOriginalFilename().substring(xlsFile.getOriginalFilename().lastIndexOf("."));
        try {
            if (StringUtils.endsWithIgnoreCase(ext, ".xls") || StringUtils.endsWithIgnoreCase(ext, "xlsx")) {
                String fileName = xlsFile.getOriginalFilename();
                boolean isE2007 = false;
                if (fileName.endsWith("xlsx")) {
                    isE2007 = true;
                }
                InputStream input = xlsFile.getInputStream();
                Workbook wb = null;
                if (isE2007) {
                    wb = new XSSFWorkbook(input);
                } else {
                    wb = new HSSFWorkbook(input);
                }
                Sheet sheet = wb.getSheetAt(0);
                int numberOfRows = sheet.getPhysicalNumberOfRows();
                if (numberOfRows <= 1) result.error("NO Content");
                //获取标题
                Row headerRow = sheet.getRow(0);
                //获取总列数
                int numberOfCells = headerRow.getPhysicalNumberOfCells();
                if (numberOfCells <= 1) result.error("No Content");
                Map<String, Integer> headerMap = new HashMap<>();
                for (int i = 0; i < numberOfCells; i++) {
                    headerMap.put(getCellValue(headerRow.getCell(i)), i);
                }
                //声明数据的集合
                List<Map<Integer, String>> dataMapList = new ArrayList<>();
                Map<Integer, String> dataMap = null;
                //循环所有数据行
                for (int i = 1; i < numberOfRows; i++) {
                    //实例化单个数据的map
                    dataMap = new HashMap<>();
                    for (int j = 0; j < numberOfCells; j++){
                        //循环赋值
                        dataMap.put(j, getCellValue(sheet.getRow(i).getCell(j)));
                    }
                    dataMapList.add(dataMap);
                }
                System.out.println(JSON.toJSONString(headerMap));
                System.out.println(JSON.toJSONString(dataMapList));
                //查出系统的启用语言
                List<Language> allLanguage = languageService.allLanguage();
                List<String> languageList = allLanguage.stream().map(Language::getCode).collect(Collectors.toList());
                //取出所有的countryCode
                Set<String> countrySet = dataMapList.stream().map(temp -> temp.get(0)).collect(Collectors.toSet());
                countrySet.forEach(temp->{
                    languageList.stream().forEach(language->{
                        if(countryService.findCountryByCodeAndLanguage(temp, language)){
                            Country country = new Country();
                            country.setLanguage(language);
                            country.setCode(temp);
                            countryService.saveCountry(country);
                        }
                    });
                });
                List<Country> countryList = countryService.findCountriesByCodeSet(countrySet);
                if(CollectionUtils.isEmpty(countryList)) result.error("No CountryList");
                Map<String, Map<String, List<Country>>> countryMap = countryList.stream().collect(Collectors.groupingBy(Country::getCode, Collectors.groupingBy(Country::getLanguage)));
                List<Country> result1 = new ArrayList<>();
                //把数据渲染到实体类中去
                for(Map<Integer, String> map : dataMapList){
                    String countryCode = map.get(0);
                    Map<String, List<Country>> countryListMap = countryMap.get(countryCode);
                    // 利用启用的语言进行过滤
                    for (String language : languageList) {
                        List<Country> countries = countryListMap.get(language);
                        // 获取该language下面的值
                        String value = map.get(headerMap.get(language));
                        if (org.apache.commons.lang3.StringUtils.isBlank(value)) continue;
                        Country country = null;
                        if (org.apache.commons.collections.CollectionUtils.isEmpty(countries)) {
                            country = new Country();
                            country.setCode(countryCode);
                            country.setLanguage(language);
                            country.setCountryName(value);
                            result1.add(country);
                        } else {
                            country = countries.get(0);
                            country.setCountryName(value);
                            result1.add(country);
                        }
                    }
                }
                countryRepository.saveAll(result1);
                result.success("SUCCESS");
            }
        } catch (Exception e) {
            result.error(e.getMessage());
        }
        return result;
    }

    @ApiOperation("导出国家列表信息")
    @RequestMapping("/v1/cms/countries/export")
    @ResponseBody
    public void exportCountries(HttpServletResponse response)
            throws Exception {
        response.setContentType("applicationnd.ms-excel; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=Countries.xlsx");
        OutputStream ops = response.getOutputStream();
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        CellStyle headStyle = initHeadCellStyle(wb); //表头样式
        List<Language> codeValueVOS = languageService.allLanguage();
        String[] heads = new String[codeValueVOS.size() + 1];
        heads[0] = "Code";
        for (int i = 0; i < codeValueVOS.size(); i++) {
            heads[i + 1] = codeValueVOS.get(i).getCode();
        }
        XSSFRow headRow = sheet.createRow(0);
        headRow.setHeightInPoints(20);
        for (int i = 0; i < heads.length; i++) {
            XSSFCell headCell = headRow.createCell(i);
            headCell.setCellValue(new XSSFRichTextString(heads[i]));
            headCell.setCellStyle(headStyle);
        }
        List<String> codeList = countryService.findCodeGroupByCode();
        for (int i = 0; i < codeList.size(); i++) {
            String code = codeList.get(i);
            List<Country> countryList = countryService.findCountriesByCode(code);
            XSSFRow row = sheet.createRow(i + 1);
            row.setHeightInPoints(20);
            row.createCell(0).setCellValue(code);
            for (int j = 0; j < countryList.size(); j++) {
                row.createCell(j + 1).setCellValue(countryList.get(j).getCountryName());
            }
        }
        adjustColumnSize(sheet, heads); //自动调整列宽
        wb.write(ops);
        ops.flush();
        ops.close();
    }

    /**
     * @Description: 自动调整列宽
     */
    private void adjustColumnSize(XSSFSheet sheet, String[] fieldNames) {
        for (int i = 0; i < fieldNames.length + 1; i++) {
            //sheet.autoSizeColumn(i, true);
            sheet.autoSizeColumn(i);
        }
    }

    /**
     * @Description: 初始化表头行样式
     */
    private CellStyle initHeadCellStyle(XSSFWorkbook wb) {
        CellStyle headStyle = wb.createCellStyle();
        headStyle.setAlignment(CellStyle.ALIGN_LEFT);
        headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        Font headFont = initHeadFont(wb);
        headStyle.setFont(headFont);
        headStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        headStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        return headStyle;
    }

    /**
     * @Description: 初始化表头行字体
     */
    private Font initHeadFont(XSSFWorkbook wb) {
        Font headFont = wb.createFont();
        headFont.setFontName("Arial");
        headFont.setFontHeightInPoints((short) 12);
        headFont.setCharSet(Font.DEFAULT_CHARSET);
        return headFont;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            return cell.getCellFormula();
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        }
        return "";
    }
}
