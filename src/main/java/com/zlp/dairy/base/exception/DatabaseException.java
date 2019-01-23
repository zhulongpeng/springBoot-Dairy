package com.zlp.dairy.base.exception;

import com.zlp.dairy.base.util.ResResult;
import org.apache.poi.ss.formula.functions.T;


public class DatabaseException extends BaseException {
    
    private static final long serialVersionUID = 1L;

    public DatabaseException() {
        super();
    }

    public DatabaseException(String errorMsg) {
        super(errorMsg);
    }

    public DatabaseException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public DatabaseException(int errorCode, String errorMsg, ResResult<T> dataResult) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.dataResult = dataResult;
    }

    public DatabaseException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }

    public DatabaseException(int errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
    }

    public DatabaseException(int errorCode, String errorMsg, ResResult<T> dataResult, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.dataResult = dataResult;
    }
}
