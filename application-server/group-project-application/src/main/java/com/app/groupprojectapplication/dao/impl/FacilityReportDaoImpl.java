package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IFacilityReportDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.FacilityReport;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FacilityReportDaoImpl implements IFacilityReportDao {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public List<FacilityReport> getFacilityReportsByEmployeeId(Integer employee_id) {
        Session session = sessionFactory.getCurrentSession();
        List<FacilityReport> facilityReportsSet = session
            .createQuery("From FacilityReport fr WHERE fr.employee.id = " + employee_id).getResultList();
        return facilityReportsSet;
    }

    @Override
    public List<FacilityReport> getFacilityReportsByStatus(String status) {
        Session session = sessionFactory.getCurrentSession();
        List<FacilityReport> facilityReportsSet = session
            .createQuery("From FacilityReport fr WHERE fr.status = " + status).getResultList();
        return facilityReportsSet;
    }

    @Override
    public void insertFacilityReport(FacilityReport facilityReport) {
        Session session = sessionFactory.getCurrentSession();
        session.save(facilityReport);
    }

    @Override
    public Employee getEmployeeById(Integer facilityReportId) {
        Session session = sessionFactory.getCurrentSession();
        List<Employee> resultList = session
            .createQuery("SELECT Employee FROM FacilityReport WHERE id = " + facilityReportId).getResultList();
        return resultList.get(0);
    }

}
