package com.zlp.dairy.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.zlp.dairy.base.constant.Constant;
import com.zlp.dairy.base.constant.ExcelUploadError;
import com.zlp.dairy.base.util.FileUtil;
import com.zlp.dairy.base.util.MD5Util;
import com.zlp.dairy.base.util.ResResult;
import com.zlp.dairy.business.entity.Issuer;
import com.zlp.dairy.business.entity.UploadResultError;
import com.zlp.dairy.business.service.ExcelService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ExcelServiceImpl implements ExcelService {

    @Value("${web.upload-path-compress}")
    private String uploadUrlForCompress;

    private static final String FH = "/";

    private static final String SPLIT_FH = ";";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResResult<List<UploadResultError>> fileUploadForIssuer(MultipartFile file) {
        ResResult<List<UploadResultError>> result = new ResResult<>();
        List<UploadResultError> list = new ArrayList<>();
        result.setData(list);
        String fileName = file.getOriginalFilename();
        try {
            PushbackInputStream pushbackInputStream = new PushbackInputStream(file.getInputStream(), FileUtil.READ_SIZE);
            String type = FileUtil.getType(pushbackInputStream);
            if(!(FileUtil.FileType.ZIP.name().equals(type))){
                result.error(ExcelUploadError.FILE_TYPE_ERROR.getCode());
                //上传压缩文件后的地址
                String[] filePath =  uploadFile(fileName, pushbackInputStream, type, result);
                if(FileUtil.FileType.ZIP.name().equals(type)){
                    analysisZIP(filePath, result);
                }
                if(result.getCode() == Constant.Code.error) return result;
                analysisExcelForIssuer(result, filePath[1]);
            }
        } catch (IOException e) {
            result.error(e.getMessage());
        }
        return null;
    }

    private void analysisExcelForIssuer(ResResult<List<UploadResultError>> result, String analysisDir) throws IOException {
        int index = 0;
        try {
            // 获取excel
            File excelFile = getExcelFile(analysisDir, result);
            if (excelFile == null) {
                result.error(ExcelUploadError.FILE_NOT_FIND.getCode());
                return;
            }
            List<Row> issuerList = new ArrayList<>();
            //获得Workbook工作薄对象
            Workbook wb = new XSSFWorkbook(new FileInputStream(excelFile));
            int numberOfSheets = wb.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = wb.getSheetAt(i);
                if (sheet.getSheetName().contains("Issuer")) {
                    jxSheet(sheet, issuerList);
                }
            }
            List<UploadResultError> errorList = new ArrayList<>();
            // 检查数据是否正确
            for (Row temp : issuerList) {
                index++;
                String logo = getCellValue(temp.getCell(4));
                String filePath = analysisDir + FH + logo;
                File file = new File(filePath);
                if (!file.exists()) {
                    Map<String, Object> params = new HashMap<>();
                    params.put("rowIndex", index + 1);
                    params.put("colIndex", 5);
                    params.put("dataIndex", index + 1);
                    UploadResultError uploadResultError = new UploadResultError(params, ExcelUploadError.IMG_NOT_FIND.getCode());
                    errorList.add(uploadResultError);
                }
            }
            if (!CollectionUtils.isEmpty(errorList)) {
                result.error();
                result.setData(errorList);
                return;
            }
            index = 0;
            Map<String, List<CodeValueVO>> languageMap = new HashMap<>();
            List<Issuer> issuers = new ArrayList<>();
            for (Row temp : issuerList) {
                Issuer issuer = new Issuer();
                String countryCode = getCellValue(temp.getCell(1));
                List<CodeValueVO> voList = languageMap.get(countryCode);
                if (CollectionUtils.isEmpty(voList)) {
                    voList = languageService.allLanguageByMarkets(Collections.singletonList(countryCode));
                    if (CollectionUtils.isEmpty(voList)) continue;
                    languageMap.put(countryCode, voList);
                }
                // 利用启用的语言进行过滤
                for (CodeValueVO vo : voList) {
                    String language = getCellValue(temp.getCell(5));
                    if (vo.getCode().equalsIgnoreCase(language)) {
                        issuer.setCountryCode(countryCode);
                        issuer.setCountryName(getCellValue(temp.getCell(2)));
                        issuer.setIssuerCode(getCellValue(temp.getCell(3)));
                        issuer.setIssuerLogo(getCellValue(temp.getCell(4)));
                        issuer.setIssuerName(getCellValue(temp.getCell(0)));
                        issuer.setLanguage(vo.getCode());
                        issuers.add(issuer);
                    }
                }
            }
            if (CollectionUtils.isEmpty(issuers)) return;
            Set<String> issuerSet = issuers.stream().map(Issuer::getIssuerCode).distinct().collect(Collectors.toSet());
            List<Issuer> oldIssuerList = issuerService.findAllByIssuerCodeSet(issuerSet);
            Map<String, Issuer> oldIssuerMap = oldIssuerList.stream().collect(Collectors.toMap(Issuer::getIssuerCode, Function.identity(), (o, n) -> o));
            List<Issuer> allIssuerForInsert = new ArrayList<>();
            Map<String, String> imgFileMap = new HashMap<>();
            for (Issuer issuer : issuers) {
                index++;
                String issuerCode = issuer.getIssuerCode();
                Issuer insertIssuer = oldIssuerMap.get(issuerCode);
                // 拷贝图片
                String issuerLogo = issuer.getIssuerLogo();
                String issuerLogoUrl = copyImg(analysisDir, uploadPath, issuerLogo, imgFileMap);
                issuer.setIssuerLogo(resourcesImgPath + issuerLogoUrl);
                if (insertIssuer == null) {
                    issuer.setIssuerId(issuerService.findByBusinessKey());
                    allIssuerForInsert.add(issuer);
                } else {
                    insertIssuer.setCountryCode(issuer.getCountryCode());
                    insertIssuer.setCountryName(issuer.getCountryName());
                    insertIssuer.setIssuerCode(issuerCode);
                    insertIssuer.setIssuerLogo(issuer.getIssuerLogo());
                    insertIssuer.setIssuerName(issuer.getIssuerName());
                    insertIssuer.setLanguage(issuer.getLanguage());
                    allIssuerForInsert.add(insertIssuer);
                }
            }
            // 插入数据库
            issuerService.saveAll(allIssuerForInsert);
        } catch (BusinessException e) {
            InsertDBErrorMO errorMO = new InsertDBErrorMO();
            errorMO.setCode(ExcelUploadError.getExcelUploadErrorByCode(e.getLocalizedMessage()));
            errorMO.setIndex(index);
            throw new RuntimeException(JSON.toJSONString(errorMO));
        }
    }

    private void analysisZIP(String[] filePath, ResResult<List<UploadResultError>> result) throws IOException {
        String charset = "UTF-8";
        File zipFile = new File(filePath[0]);
        ZipFile zipFileObj = null;
        String analysisDir = filePath[1];
        try {
            zipFileObj = new ZipFile(zipFile, Charset.forName(charset));
            final Enumeration<ZipEntry> em = (Enumeration<ZipEntry>) zipFileObj.entries();
            ZipEntry zipEntry;
            File outItemFile;
            while (em.hasMoreElements()) {
                zipEntry = em.nextElement();
                outItemFile = new File(new File(analysisDir), zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    outItemFile.mkdirs();
                } else {
                    if (false == outItemFile.exists()) {
                        final File parentFile = outItemFile.getParentFile();
                        if (null != parentFile && false == parentFile.exists()) {
                            parentFile.mkdirs();
                        }
                        outItemFile.createNewFile();
                    }
                    try (InputStream in = zipFileObj.getInputStream(zipEntry); OutputStream out = new BufferedOutputStream(new FileOutputStream(outItemFile))) {
                        IOUtils.copy(in, out);
                    }
                }
            }
        } catch (Exception e) {
            result.error(ExcelUploadError.ZIP_ANALYSIS_ERROR.getCode());
        } finally {
            if (zipFileObj != null) {
                IOUtils.closeQuietly(zipFileObj);
            }
        }
    }

    private String[] uploadFile(String fileName, PushbackInputStream ins, String type, ResResult<List<UploadResultError>> result) {
        String curDate = String.valueOf(System.currentTimeMillis());
        String uploadDir =  uploadUrlForCompress + curDate;
        File dirFile = new File(uploadDir);
        if(!dirFile.exists()) dirFile.mkdir();
        String[] fileStrs = new String[2];
        try {
            String analysisDir = uploadDir + FH + MD5Util.md5(fileName) + curDate;
            String filePath = analysisDir + "." + type.toLowerCase();
            fileStrs[0] = filePath;
            fileStrs[1] = analysisDir;
            File file = new File(filePath);
            if (!file.exists()) {
                saveFile(ins, filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.error(ExcelUploadError.SYSTEM_ERROR.getCode());
        } finally {
            if (fileStrs[0] == null) {
                result.error(ExcelUploadError.SYSTEM_ERROR.getCode());
            }
        }
        return fileStrs;
    }

    private void saveFile(InputStream ins, String filePath) throws IOException {
        OutputStream os = new FileOutputStream(filePath);
        int bytesRead = 0;
        byte[] buffer = new byte[1024];
        while ((bytesRead = ins.read(buffer, 0, 1024)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
    }
}
