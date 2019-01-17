package com.zlp.dairy.business.entity;

import com.zlp.dairy.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_issuer_product")
public class IssuerProduct extends BaseEntity {

    private static final long serialVersionUID = 5695349546026071474L;

    private String productId;

    private String productName;

    private String productCode;

    private String mastercardProducts;

    private String issuerId;

    private String issuerName;

    private String cardCategory;

    private String applyUrl;

    private String sampleCardImage;

    private String headline;

    private String cardValuePropositionFirst;

    private String cardValuePropositionSecond;

    private String cardValuePropositionThird;

    private String backgroundColor;

    private String textColor;

    private String language;

    @Column(name = "product_id")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column(name = "product_code")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Column(name = "mastercard_product")
    public String getMastercardProducts() {
        return mastercardProducts;
    }

    public void setMastercardProducts(String mastercardProducts) {
        this.mastercardProducts = mastercardProducts;
    }

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

    @Column(name = "card_category")
    public String getCardCategory() {
        return cardCategory;
    }

    public void setCardCategory(String cardCategory) {
        this.cardCategory = cardCategory;
    }

    @Column(name = "apply_url")
    public String getApplyUrl() {
        return applyUrl;
    }

    public void setApplyUrl(String applyUrl) {
        this.applyUrl = applyUrl;
    }

    @Column(name = "sample_card_image")
    public String getSampleCardImage() {
        return sampleCardImage;
    }

    public void setSampleCardImage(String sampleCardImage) {
        this.sampleCardImage = sampleCardImage;
    }

    @Column(name = "headline")
    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    @Column(name = "card_value_proposition_first")
    public String getCardValuePropositionFirst() {
        return cardValuePropositionFirst;
    }

    public void setCardValuePropositionFirst(String cardValuePropositionFirst) {
        this.cardValuePropositionFirst = cardValuePropositionFirst;
    }

    @Column(name = "card_value_proposition_second")
    public String getCardValuePropositionSecond() {
        return cardValuePropositionSecond;
    }

    public void setCardValuePropositionSecond(String cardValuePropositionSecond) {
        this.cardValuePropositionSecond = cardValuePropositionSecond;
    }

    @Column(name = "card_value_proposition_third")
    public String getCardValuePropositionThird() {
        return cardValuePropositionThird;
    }

    public void setCardValuePropositionThird(String cardValuePropositionThird) {
        this.cardValuePropositionThird = cardValuePropositionThird;
    }

    @Column(name = "background_color")
    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Column(name = "text_color")
    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
