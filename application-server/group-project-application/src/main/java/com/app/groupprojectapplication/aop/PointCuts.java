package com.app.groupprojectapplication.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PointCuts {
    @Pointcut("within(com.app.groupprojectapplication.dao..*)")
    public void inDaoLayer() {}

    @Pointcut("bean(*Controller)")
    public void inControllerLayer() {}
}
