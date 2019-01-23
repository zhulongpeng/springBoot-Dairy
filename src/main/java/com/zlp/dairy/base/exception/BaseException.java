package com.zlp.dairy.base.exception;

import com.zlp.dairy.base.util.ResResult;
import org.apache.poi.ss.formula.functions.T;

public abstract class BaseException extends Exception {

    private static final long serialVersionUID = 1L;

    protected int errorCode;

    protected ResResult<T> dataResult;

    protected BaseException() {
        super();
    }

    protected BaseException(String errorMsg) {
        super(errorMsg);
    }

    protected BaseException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    protected BaseException(int errorCode, String errorMsg, ResResult<T> dataResult) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.dataResult = dataResult;
    }

    protected BaseException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }

    protected BaseException(int errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
    }

    protected BaseException(int errorCode, String errorMsg, ResResult<T> dataResult, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.dataResult = dataResult;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ResResult<T> getDataResult() {
        return dataResult;
    }

    public void setDataResult(ResResult<T> dataResult) {
        this.dataResult = dataResult;
    }
}
