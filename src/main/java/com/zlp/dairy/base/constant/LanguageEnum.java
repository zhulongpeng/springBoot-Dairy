package com.zlp.dairy.base.constant;

import com.zlp.dairy.business.entity.Language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum LanguageEnum {

    EN_SG("en_SG", "English(Singapore)", ""),
    EN_IN("en_IN", "English(India)", ""),
    SC_CN("sc-CN", "Chinese(China)", "");

    private String code;

   private String value;

   private String desc;

    LanguageEnum(String code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private static final List<Language> list = new ArrayList();

    private static  final Map<String, LanguageEnum> map = new HashMap<>();

    private static final Map<String, LanguageEnum> descMap = new HashMap<>();

    static {
        for(LanguageEnum languageEnum : LanguageEnum.values()){
            map.put(languageEnum.getCode(), languageEnum);
            list.add(new Language(languageEnum.getCode(), languageEnum.getValue()));
        }
        descMap.put("en", LanguageEnum.EN_SG);
        descMap.put("in", LanguageEnum.EN_IN);
        descMap.put("zn", LanguageEnum.SC_CN);
    }

    public static List<Language> getList(){
        return list;
    }

    public static LanguageEnum getLanguageByCode(String code){
        LanguageEnum languageEnum = map.get(code);
        return languageEnum == null ? descMap.get(code.substring(0,2)) : languageEnum;
    }
}
