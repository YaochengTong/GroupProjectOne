package com.app.groupprojectapplication.aop;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletResponseWrapper;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class LoggingAOPZhongqiu {

    private static final Gson gson = new Gson();

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("com.app.groupprojectapplication.aop.PointCuts.inDaoLayer()")
    public Object executionTimeAdvice(ProceedingJoinPoint pjp) throws Throwable {
        String signature = pjp.getSignature().toString();
        Object clazz = pjp.getSignature().getDeclaringType();

        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime;

        log.info("class: "+clazz);
        log.info(signature+ " execution time: "+elapsedTime+" ms");
        return result;
    }

    @Before("com.app.groupprojectapplication.aop.PointCuts.inControllerLayer()")
    public void beforeControllerAdvice(JoinPoint joinPoint) {
        Object[] objs = joinPoint.getArgs();
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Map<String, Object> paramMap = new HashMap<>();
        for (int i = 0; i < objs.length; i++) {
            if (!(objs[i] instanceof ExtendedServletRequestDataBinder)
                    && !(objs[i] instanceof HttpServletResponseWrapper)) {
                paramMap.put(argNames[i], objs[i]);
            }
        }
        if (paramMap.size() > 0) {
            log.info("\n[{}]method:{}\nparameter:{}", joinPoint.getSignature(), paramMap);
        }
    }

    @AfterReturning(value="com.app.groupprojectapplication.aop.PointCuts.inControllerLayer()", returning = "res")
    public void afterControllerAdvice(Object res) {
        log.info("[{}]Response content:{}", gson.toJson(res));
    }
}
