package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IFacilityReport;
import com.app.groupprojectapplication.domain.Facility;
import com.app.groupprojectapplication.domain.FacilityReport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class FacilityReportDaoImpl implements IFacilityReport {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public List<FacilityReport> getFacilityReportsByEmployeeId(Integer employee_id) {
        Session session = sessionFactory.getCurrentSession();
        List<FacilityReport> facilityReportsSet = session.createQuery("From FacilityReport fr WHERE fr.employee.id = " + employee_id).getResultList();
        return facilityReportsSet;
    }

    @Override
    public List<FacilityReport> getFacilityReportsByStatus(String status) {
        Session session = sessionFactory.getCurrentSession();
        List<FacilityReport> facilityReportsSet = session.createQuery("From FacilityReport fr WHERE fr.status = " + status).getResultList();
        return facilityReportsSet;
    }

    @Override
    public void insertFacilityReport(FacilityReport facilityReport) {
        Session session = sessionFactory.getCurrentSession();
        session.save(facilityReport);
    }
}
