package com.zlp.dairy.business.entity;

import com.zlp.dairy.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_issuer")
public class Issuer extends BaseEntity {

    private static final long serialVersionUID = -8338061990736452072L;

    private String issuerId;

    private String issuerName;

    private String issuerCode;

    private String countryCode;

    private String countryName;

    private String issuerLogo;

    private String language;

    @Column(name = "issuer_id")
    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    @Column(name = "issuer_name")
    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    @Column(name = "issuer_code")
    public String getIssuerCode() {
        return issuerCode;
    }

    public void setIssuerCode(String issuerCode) {
        this.issuerCode = issuerCode;
    }

    @Column(name = "country_code")
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Column(name = "country_name")
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Column(name = "issuer_logo")
    public String getIssuerLogo() {
        return issuerLogo;
    }

    public void setIssuerLogo(String issuerLogo) {
        this.issuerLogo = issuerLogo;
    }

    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
