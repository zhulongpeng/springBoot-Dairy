package com.zlp.dairy.business.service.impl;

import com.zlp.dairy.base.constant.Constant;
import com.zlp.dairy.base.constant.ExcelUploadError;
import com.zlp.dairy.base.util.FileUtil;
import com.zlp.dairy.base.util.MD5Util;
import com.zlp.dairy.base.util.ResResult;
import com.zlp.dairy.business.entity.UploadResultError;
import com.zlp.dairy.business.service.ExcelService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelServiceImpl implements ExcelService {

    @Value("${web.upload-path-compress}")
    private String uploadUrlForCompress;

    private static final String FH = "/";

    private static final String SPLIT_FH = ";";

    private static final String SHEET_NAME = "FILL THIS ONLY - OFFER SHEET";

    private static final String SHEET_NAME_FOR_MASTER = "FILL THIS ONLY - OFFER SHEET";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResResult<List<UploadResultError>> fileUploadForIssuer(MultipartFile file) {
        ResResult<List<UploadResultError>> result =  new ResResult<>();
        List<UploadResultError> list = new ArrayList<>();
        result.setData(list);
        String fileName =  file.getOriginalFilename();
        try {
            PushbackInputStream pushbackInputStream = new PushbackInputStream(file.getInputStream(), FileUtil.READ_SIZE);
            String type = FileUtil.getType(pushbackInputStream);
            if(!(FileUtil.FileType.ZIP.name().equals(type))){
                result.error(ExcelUploadError.FILE_TYPE_ERROR.getCode());
                //上传压缩文件之后的地址
                String[] filepath = uploadFile(fileName, pushbackInputStream, type, result);
                if(FileUtil.FileType.ZIP.name().equals(type))
                    analysisZIP(filepath, result);
                if(result.getCode() == Constant.Code.error) return result;
                analysisExcelForIssuer(result, filepath[1]);
            }
        } catch (IOException e) {
            result.error(e.getMessage());
        }
        return result;
    }

    private void analysisExcelForIssuer(ResResult<List<UploadResultError>> result, String s) {

    }

    private void analysisZIP(String[] filepath, ResResult<List<UploadResultError>> result) {

    }

    private String[] uploadFile(String fileName, InputStream inputStream, String type, ResResult<List<UploadResultError>> result) {
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
            if(!file.exists()){
                saveFile(inputStream, filePath);
            }
        } catch (Exception e) {
            result.error(e.getMessage());
        } finally {
            if(fileStrs[0] == null){
                result.error(ExcelUploadError.SYSTEM_ERROR.getCode());
            }
        }
        return fileStrs;
    }

    private void saveFile(InputStream inputStream, String filePath) {

    }
}
