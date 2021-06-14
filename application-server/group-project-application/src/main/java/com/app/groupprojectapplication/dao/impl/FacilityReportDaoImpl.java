package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IFacilityReport;
import com.app.groupprojectapplication.domain.Facility;
import com.app.groupprojectapplication.domain.FacilityReport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FacilityReportDaoImpl implements IFacilityReport {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public List<FacilityReport> getFacilityReportsByEmployeeId(Integer employee_id) {
        Session session = sessionFactory.openSession();
        List<FacilityReport> facilityReportsSet = session.createQuery("From FacilityReport fr WHERE fr.employee.id = " + employee_id).getResultList();
        session.close();
        return facilityReportsSet;
    }

    @Override
    public List<FacilityReport> getFacilityReportsByStatus(String status) {
        Session session = sessionFactory.openSession();
        List<FacilityReport> facilityReportsSet = session.createQuery("From FacilityReport fr WHERE fr.status = " + status).getResultList();
        session.close();
        return facilityReportsSet;
    }

    @Override
    public void insertFacilityReport(FacilityReport facilityReport) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.save(facilityReport);
        ts.commit();
        session.close();
    }
}
