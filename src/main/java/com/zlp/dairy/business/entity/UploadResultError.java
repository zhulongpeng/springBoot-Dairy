package com.zlp.dairy.business.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Map;

public class UploadResultError implements Serializable {

    private static final long serialVersionUID = 7947720518337203568L;

    @ApiModelProperty("行号")
    private Integer rowIndex;

    @ApiModelProperty("列号")
    private Integer colIndex;

    @ApiModelProperty("数据编码")
    private Integer dataIndex;

    @ApiModelProperty("错误信息")
    private String message;

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Integer getColIndex() {
        return colIndex;
    }

    public void setColIndex(Integer colIndex) {
        this.colIndex = colIndex;
    }

    public Integer getDataIndex() {
        return dataIndex;
    }

    public void setDataIndex(Integer dataIndex) {
        this.dataIndex = dataIndex;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UploadResultError(Integer rowIndex, Integer colIndex, Integer dataIndex, String message) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.dataIndex = dataIndex;
        this.message = message;
    }

    public UploadResultError(Map<String,Object> params, String message) {
        this.rowIndex = (Integer) params.get("rowIndex");
        this.colIndex = (Integer) params.get("colIndex");
        this.dataIndex = (Integer) params.get("dataIndex");
        this.message = message;
    }
}
