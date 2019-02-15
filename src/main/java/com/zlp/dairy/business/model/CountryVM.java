package com.zlp.dairy.business.model;

import io.swagger.annotations.Api;

import java.io.Serializable;

@Api(value = "country的视图基本信息")
public class CountryVM implements Serializable {

    private static final long serialVersionUID = 3888378737108258223L;

    private String countryId;

    private String code;

    private String countryName;

    private String language;

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
