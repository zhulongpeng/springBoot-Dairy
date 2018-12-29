package com.zlp.dairy.base.util;


import com.zlp.dairy.base.constant.Constant;
import io.swagger.annotations.ApiModelProperty;

public class ResResult<T> {

    @ApiModelProperty(value = "code : 返回代码，1表示OK，其它的都有对应问题")
    private int code = Constant.Code.success;

    @ApiModelProperty(value = "message : 如果code!=1,错误信息")
    private String message = Constant.Message.success;

    @ApiModelProperty(value = "code为1时返回结果集")

    private T data = (T) new Object();

    private int count;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResResult() {
        this.data = (T) new Object();
    }

    public ResResult(String errorMsg) {
        this.message = errorMsg;
        this.code = Constant.Code.error;
        this.data = null;
    }

    public void success(T data) {
        this.code = Constant.Code.success;
        this.message = Constant.Message.success;
        this.data = data;
    }

    public void error() {
        this.code = Constant.Code.error;
        this.message = Constant.Message.error;
        this.data = null;
    }

    public void error(String message) {
        this.code = Constant.Code.error;
        this.message = message;
        this.data = null;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

