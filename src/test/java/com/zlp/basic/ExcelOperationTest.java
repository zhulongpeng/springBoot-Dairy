package com.zlp.basic;

import com.alibaba.fastjson.JSON;
import com.zlp.DairyApplicationTests;
import com.zlp.dairy.base.util.DateProcessUtil;
import com.zlp.dairy.business.entity.Country;
import com.zlp.dairy.business.entity.Language;
import com.zlp.dairy.business.service.CountryService;
import com.zlp.dairy.business.service.LanguageService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;


public class ExcelOperationTest extends DairyApplicationTests {

    @Autowired
    private LanguageService languageService;

    @Autowired
    private CountryService countryService;

    @Test
    public void importExcelTest() throws Exception {
        File file = new File("C:/Users/Administrator/Desktop/country_template_export.xlsx");
        Workbook wb = new XSSFWorkbook(new FileInputStream(file));
        Sheet sheet = wb.getSheetAt(0);
        //判断总行数
        int numberOfRows = sheet.getPhysicalNumberOfRows();
        System.out.println("numberOfRows:" + numberOfRows);
        if (numberOfRows <= 1) return;
        //获取标题
        Row headRow = sheet.getRow(0);
        //获取总列数
        int numberOfCells = headRow.getPhysicalNumberOfCells();
        System.out.println("numberOfCells:" + numberOfCells);
        if (numberOfCells <= 1) return;
        Map<Integer, String> headerMap = new HashMap<>();
        for (int i = 0; i < numberOfCells; i++) {
            headerMap.put(i, getCellValue(headRow.getCell(i)));
        }
        //返回Excel中的头文件
        headerMap.forEach((k, v) -> {
            System.out.println(k + " " + v);
        });
        //声明数据的集合
        List<Map<Integer, String>> dataMapList = new ArrayList<>();
        Map<Integer, String> dataMap = null;
        //循环所有数据行
        for (int i = 1; i < numberOfRows; i++) {
            //实例化单个数据的map
            dataMap = new HashMap<>();
            for (int j = 0; j < numberOfCells; j++) {
                //循环赋值
                dataMap.put(j, getCellValue(sheet.getRow(i).getCell(j)));
                System.out.println("dataMap数据:" + JSON.toJSONString(dataMap));
            }
            dataMapList.add(dataMap);
        }
        System.out.println("^^^^^^^^^^^^^^^^^^^");
        dataMapList.forEach(temp -> {
            temp.forEach((k, v) -> {
                System.out.println(k + "  " + v);
            });
        });
        System.out.println(JSON.toJSONString(headerMap));
        System.out.println("**********************");
        System.out.println("dataMapList数据：" + JSON.toJSONString(dataMapList));
        List<Language> allLanguage = languageService.allLanguage();
        List<String> languageList = allLanguage.stream().map(Language::getCode).collect(Collectors.toList());
        System.out.println("languageList:" + JSON.toJSONString(languageList));
        //取出所有的countryCode
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        List<String> collect = dataMapList.stream().map(temp -> temp.get(0)).collect(Collectors.toList());
        System.out.println("测试dataMapList.get(0)数据：" + JSON.toJSONString(collect));
        Set<String> countrySet = dataMapList.stream().map(temp -> temp.get(0)).collect(Collectors.toSet());
        List<Country> countryList = countryService.findCountriesByCodeSet(countrySet);
        System.out.println("countryList数据：" + countryList);
        if (CollectionUtils.isEmpty(countryList)) return;
        Map<String, List<Country>> collect1 = countryList.stream().collect(Collectors.groupingBy(Country::getCode));
        Map<String, Map<String, List<Country>>> countryMap = countryList.stream().collect(Collectors.groupingBy(Country::getCode, Collectors.groupingBy(Country::getLanguage)));
        List<Country> result = new ArrayList<>();
        //把数据渲染到实体类中去
        for (Map<Integer, String> map : dataMapList) {
            String countryCode = map.get(0);
            Map<String, List<Country>> countryListMap = countryMap.get(countryCode);



        }


    }

    private String getCellValue(Cell cell) {
        if (cell == null) return null;
        String cellValue = null;
        //判断数据的类型
        switch (cell.getCellType()) {
            //数字
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    cellValue = DateProcessUtil.getStrDate(date, DateProcessUtil.YYYYMMDD);
                } else {
                    DecimalFormat df = new DecimalFormat();
                    cellValue = df.format(cell.getNumericCellValue());
                }
                break;
            //字符串
            case Cell.CELL_TYPE_STRING:
                String stringCellValue = cell.getStringCellValue();
                cellValue = new String(stringCellValue.getBytes(), Charset.forName("UTF-8"));
                break;
            //boolean类型
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            //公式类型
            case Cell.CELL_TYPE_FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                //空值
            case Cell.CELL_TYPE_BLANK:
                cellValue = "";
                break;
            //错误
            case Cell.CELL_TYPE_ERROR:
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
}
