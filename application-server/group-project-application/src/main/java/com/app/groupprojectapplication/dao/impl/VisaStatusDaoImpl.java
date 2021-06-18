package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IVisaStatusDao;
import com.app.groupprojectapplication.domain.VisaStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public class VisaStatusDaoImpl implements IVisaStatusDao {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public Integer insertVisa(VisaStatus visaStatus) {
        Session session = sessionFactory.getCurrentSession();
        Integer id = (Integer) session.save(visaStatus);
        return id;
    }

    @Override
    public List<VisaStatus> getVisaByType(String visaType) {
        Session session = sessionFactory.getCurrentSession();
        List<VisaStatus> visaStatusList = session.createQuery("FROM VisaStatus v WHERE v.visaType = '" + visaType + "'").getResultList();
        return visaStatusList;
    }

    @Override
    public Integer getVisaAuthorizationLeftDay(Integer employeeId) {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT datediff(date(visa_end_date), current_date()) AS DateDiff FROM hr_db.employee WHERE id = " + String.valueOf(employeeId);
        Integer leftDay = ((BigInteger) session.createNativeQuery(query).list().get(0)).intValue();
        return leftDay;
    }

    @Override
    public String getVisaTypeByEmployeeId(Integer employeeId) {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT v.visa_type FROM hr_db.visa_status v LEFT JOIN hr_db.employee e ON v.id = e.visa_status_id WHERE e.id = " + employeeId;
        String visaType = (String)session.createNativeQuery(query).list().get(0);
        return visaType;
    }
}
