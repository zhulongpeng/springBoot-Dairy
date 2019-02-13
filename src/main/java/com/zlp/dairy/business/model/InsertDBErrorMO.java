package com.zlp.dairy.business.model;

import java.io.Serializable;

public class InsertDBErrorMO implements Serializable {

    private static final long serialVersionUID = 7908884695172932224L;

    private Integer index;

    private String code;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
