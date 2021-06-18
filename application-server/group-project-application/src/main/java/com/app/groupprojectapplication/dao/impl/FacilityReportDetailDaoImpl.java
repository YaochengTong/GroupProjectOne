package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IFacilityReportDetailDao;
import com.app.groupprojectapplication.domain.FacilityReportDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class FacilityReportDetailDaoImpl implements IFacilityReportDetailDao {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public void insertFailicyReportDetail(FacilityReportDetail facilityReportDetail) {
        Session session = sessionFactory.getCurrentSession();
        session.save(facilityReportDetail);
    }
}
