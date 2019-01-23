package com.zlp.dairy.base.exception;

import com.zlp.dairy.base.util.ResResult;
import org.apache.poi.ss.formula.functions.T;

public class BusinessException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BusinessException() {
        super();
    }

    public BusinessException(String errorMsg) {
        super(errorMsg);
    }

    public BusinessException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public BusinessException(int errorCode, String errorMsg, ResResult<T> dataResult) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.dataResult = dataResult;
    }

    public BusinessException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }

    public BusinessException(int errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
    }

    public BusinessException(int errorCode, String errorMsg, ResResult<T> dataResult, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.dataResult = dataResult;
    }
}
