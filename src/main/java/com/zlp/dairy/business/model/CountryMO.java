package com.zlp.dairy.business.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.Api;

import java.io.Serializable;

@Api(value = "country入参的基本信息")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryMO implements Serializable {

    private static final long serialVersionUID = -394652905631216120L;

    private String countryId;

    private String countryName;

    private String code;

    private String language;

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

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
