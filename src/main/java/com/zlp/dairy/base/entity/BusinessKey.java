package com.zlp.dairy.base.entity;

import io.swagger.annotations.ApiModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_business_key")
@ApiModel(value = "业务编码表")
public class BusinessKey extends BaseEntity {

    private String businessKey;

    private String numberedDate;

    private Integer serialNumber;

    private String prefix;

    @Column(name = "business_key")
    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    @Column(name = "numbered_date")
    public String getNumberedDate() {
        return numberedDate;
    }

    public void setNumberedDate(String numberedDate) {
        this.numberedDate = numberedDate;
    }

    @Column(name = "serial_number")
    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Column(name = "prefix")
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
