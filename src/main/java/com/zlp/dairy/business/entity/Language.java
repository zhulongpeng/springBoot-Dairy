package com.zlp.dairy.business.entity;

import com.zlp.dairy.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_language")
@ApiModel(value = "语言定义表")
public class Language extends BaseEntity {

    private String language;

    private String code;

    public Language() {
    }

    public Language(String code, String language) {
        this.language = language;
        this.code = code;
    }

    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
