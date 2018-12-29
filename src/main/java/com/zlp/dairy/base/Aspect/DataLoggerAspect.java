package com.zlp.dairy.base.Aspect;

import com.alibaba.fastjson.JSON;
import com.zlp.dairy.base.util.XaUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Order
@Component
public class DataLoggerAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.zlp.dairy.business.controller..*.*(..))")
    public void webLog() {
    }


    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) throws Throwable {
        startTime.set(System.currentTimeMillis());
        logger.info("start time  ===>>> start:{}", XaUtil.getToDayStr());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String http_method = request.getMethod();
        String ip = request.getRemoteAddr();
        String class_method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("URL : " + url);
        logger.info("HTTP_METHOD : " + http_method);
        logger.info("IP : " + ip);
        logger.info("CLASS_METHOD : " + class_method);
        logger.info("The user in operation at this time :{}");
        logger.info("ARGS : " + args);
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + JSON.toJSONString(ret));
        logger.info("end time  ===>>> end:{}", XaUtil.getToDayStr());
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }
}
