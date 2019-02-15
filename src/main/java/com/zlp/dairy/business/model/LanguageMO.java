package com.zlp.dairy.business.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel("language基本信息")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LanguageMO implements Serializable {

    private static final long serialVersionUID = 4155081247310502594L;

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
