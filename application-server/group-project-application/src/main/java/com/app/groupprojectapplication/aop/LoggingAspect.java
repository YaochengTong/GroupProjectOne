package com.app.groupprojectapplication.aop;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Component
@Aspect
public class LoggingAspect {
    Logger log = LoggerFactory.getLogger((this.getClass()));

    @Around("com.app.groupprojectapplication.aop.PointCuts.inDaoLayer()")
    public Object daoExecutionDurationAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String signature = proceedingJoinPoint.getTarget().getClass().getName().toString().split("\\.")[5];

        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime;

        log.info(signature + " execution time: " + elapsedTime +" ms");

        return result;
    }

    @Around("com.app.groupprojectapplication.aop.PointCuts.inProfileController()")
    public void profileControllerExecutionDurationAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String signature = proceedingJoinPoint.getTarget().getClass().getName().toString().split("\\.")[4];

        long startTime = System.currentTimeMillis();
        proceedingJoinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime;

        log.info(signature + " execution time: " + elapsedTime +" ms");
    }

//    @Around("com.app.groupprojectapplication.aop.PointCuts.inEndPoints()")
//    public void getRequestAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        log.info("" + proceedingJoinPoint.getSignature().getClass());
//        Object[] objs = proceedingJoinPoint.getArgs();
//        String[] argNames = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        for (int i = 0; i < objs.length; i++) {
//            if (!(objs[i] instanceof ExtendedServletRequestDataBinder)) {
//                paramMap.put(argNames[i], objs[i]);
//            }
//        }
//        log.info("\nrequest: {}\nparameter:{}", proceedingJoinPoint.getSignature(), paramMap);
//        Object result = proceedingJoinPoint.proceed();
//        log.info("response: {}" + result.toString());
//    }

    @AfterThrowing(value = "bean(visaStatusController)", throwing = "ex")
    public void afterThrowingAdvice(Exception ex) {
        log.error(ex.toString());

    }
}
