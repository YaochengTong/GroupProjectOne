package com.app.groupprojectapplication.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {

    @Pointcut("within(com.app.groupprojectapplication.dao..*)")
    public void inDaoLayer() {}

    @Pointcut("bean(*IHouseDAO)")
    public void inHouseDaoLayer() { }

    @Pointcut("bean(*Service)")
    public void inServiceLayer() {}

    @Pointcut("bean(*Controller)")
    public void inEndPoints() {}

}
