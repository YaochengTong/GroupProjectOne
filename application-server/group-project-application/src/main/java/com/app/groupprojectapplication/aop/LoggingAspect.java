package com.app.groupprojectapplication.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("com.app.groupprojectapplication.aop.PointCuts.inDaoLayer()")
    public Object executionTimeAdvice(ProceedingJoinPoint pjp) throws Throwable {
        String signature = pjp.getSignature().toString();
        Object[] parameters = pjp.getArgs();
        Object clazz = pjp.getSignature().getDeclaringType();

        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime;

        log.info("class: " + clazz);
        log.info(signature + " execution time: " + elapsedTime + " ms");
        log.info("return value: " + result.toString());
        return result;
    }

    @AfterThrowing(value = "bean(housingController)", throwing = "e")
    public void afterThrowingAdvice(Exception e) {
        log.error(e.toString());
    }

}
