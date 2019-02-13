package com.zlp.dairy.business.model;

import io.swagger.annotations.Api;

import java.io.Serializable;

@Api("language基本信息")
public class LanguageMO implements Serializable {

    private static final long serialVersionUID = -2165692868569258738L;

    private String language;

    private String code;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LanguageMO(String language, String code) {
        this.language = language;
        this.code = code;
    }
}
