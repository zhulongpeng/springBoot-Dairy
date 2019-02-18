package com.zlp.dairy.business.model;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel("Language出参对象")
public class LanguageVM implements Serializable {

    private static final long serialVersionUID = 1566668138813689286L;

    private String code;

    private String language;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
