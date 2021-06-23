package com.app.groupprojectapplication.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PointCuts {

    @Pointcut("within(com.app.groupprojectapplication.dao.IUserDao)")
    public void inUserDaoLayer() {}

    @Pointcut("bean(*IPersonDao)")
    public void inPersonDaoLayer() {}

    @Pointcut("execution(* com.app.groupprojectapplication.dao.IEmployeeDao.get*(..))")
    public void inEmployeeDaoLayer() {}

    @Pointcut("within(com.app.groupprojectapplication.dao.impl.*)")
    public void inDaoLayer() {}

    @Pointcut("bean(*Controller)")
    public void inEndPoints() {}


}