package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IFacilityReportDetailDao;
import com.app.groupprojectapplication.domain.FacilityReport;
import com.app.groupprojectapplication.domain.FacilityReportDetail;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FacilityReportDetailDaoImpl implements IFacilityReportDetailDao {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public FacilityReportDetail getFacilityReportDetailById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(FacilityReportDetail.class, id);
    }

    @Override
    public FacilityReport getFacilityReportByReportDetailId(Integer fId) {
        Session session = sessionFactory.getCurrentSession();
        List<FacilityReport> resultList = session
            .createQuery("SELECT FacilityReport FROM FacilityReportDetail WHERE id =" + fId).getResultList();
        return resultList.get(0);
    }

    @Override
    public void insertFailicyReportDetail(FacilityReportDetail facilityReportDetail) {
        Session session = sessionFactory.getCurrentSession();
        session.save(facilityReportDetail);
    }

}
