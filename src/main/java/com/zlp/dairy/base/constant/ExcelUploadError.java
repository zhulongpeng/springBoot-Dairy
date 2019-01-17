package com.zlp.dairy.base.constant;

import java.util.HashMap;
import java.util.Map;

public enum ExcelUploadError {
    FILE_IS_NULL("FILE_IS_NULL", "文件为空"),
    FILE_NOT_FIND("FILE_NOT_FIND", "解压后没有找到excel"),
    FILE_TYPE_ERROR("FILE_TYPE_ERROR", "文件类型错误，必须为ZIP"),
    SYSTEM_ERROR("SYSTEM_ERROR", "系统异常"),
    ZIP_ANALYSIS_ERROR("ZIP_ANALYSIS_ERROR", "ZIP解压文件错误。"),
    SHEET_NOT_FIND("SHEET_NOT_FIND", "没有找到要导入的数据页(sheet)，请按模板中的数据页(sheet)来导入"),
    UPLOAD_ERROR("UPLOAD_ERROR", "导入出错！请查看详细信息。"),
    INSERT_DB_ERROR("INSERT_DB_ERROR", "插入数据库报错。"),
    CATEGORY_NOT_FIND("CATEGORY_NOT_FIND", "目录信息没有找到。"),
    COUNTRY_NOT_FIND("COUNTRY_NOT_FIND", "国家信息没有找到。"),
    IMG_NOT_FIND("IMG_NOT_FIND", "图片文件缺失。"),
    PDF_NOT_FIND("PDF_NOT_FIND", "PDF文件缺失。"),
    IMG_COPY_ERROR("IMG_COPY_ERROR", "图片拷贝异常。"),
    PDF_COPY_ERROR("PDF_COPY_ERROR", "PDF文件拷贝异常。"),
    IMG_TYPE_ERROR("IMG_TYPE_ERROR", "图片类型错误。"),
    PDF_TYPE_ERROR("PDF_TYPE_ERROR", "PDF类型错误。"),
    VALUE_IS_NULL("VALUE_IS_NULL", "该值不能为空。"),
    CHAR_IS_TO_LONG_200("CHAR_IS_TO_LONG_200", "字符过多,最多200"),
    CHAR_IS_TO_LONG_2500("CHAR_IS_TO_LONG_2500", "字符过多,最多2500"),
    CHAR_IS_TO_LONG_10000("CHAR_IS_TO_LONG_10000", "字符过多,最多10000"),
    DATE_FORMAT_ERROR("DATE_FORMAT_ERROR", "日期格式错误,只能为 YYYY-MM-DD 这种格式。");
    private String code;
    private String name;

    ExcelUploadError(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private static final Map<String, ExcelUploadError> map = new HashMap<>();

    static {
        for (ExcelUploadError error : ExcelUploadError.values()) {
            map.put(error.getCode(), error);
        }
    }

    public static String getExcelUploadErrorByCode(String code) {
        ExcelUploadError error = map.get(code);
        return error == null ? null : error.getCode();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
