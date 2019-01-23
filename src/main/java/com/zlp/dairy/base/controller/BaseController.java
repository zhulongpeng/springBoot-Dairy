package com.zlp.dairy.base.controller;

import com.zlp.dairy.base.exception.BusinessException;
import com.zlp.dairy.base.exception.DatabaseException;
import com.zlp.dairy.base.util.ResResult;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ValidationException;

/**
 * 基础控制器类
 * @author zj
 *
 */
public abstract class BaseController {

    private static final Logger LOGGER = Logger.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    ResResult<T> handleUncaughtException(Exception ex) {			//系统异常
        LOGGER.error(ex.getMessage(), ex.getCause());
        return new ResResult<T>(ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public @ResponseBody
    ResResult<T> handleValidationException(ValidationException validationEx) {		//数据校验异常
        LOGGER.error(validationEx.getMessage(), validationEx.getCause());
        return new ResResult<T>(validationEx.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public @ResponseBody
    ResResult<T> handleBusinessException(BusinessException businessEx) {	//业务逻辑异常
        LOGGER.error(businessEx.getMessage(), businessEx.getCause());
        return new ResResult<T>(businessEx.getMessage());
    }

    @ExceptionHandler(DatabaseException.class)
    public @ResponseBody
    ResResult<T> handleValidationException(DatabaseException dbEx) {		//数据库操作异常
        LOGGER.error(dbEx.getMessage(), dbEx.getCause());
        return new ResResult<T>(dbEx.getMessage());
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    public @ResponseBody
    ResResult<T> handleJSONConvertException(HttpMessageNotWritableException jsonEx) {	//JSON格式转换异常
        LOGGER.error(jsonEx.getMessage(), jsonEx.getCause());
        return new ResResult<T>("JSON格式转换异常");
    }
}
