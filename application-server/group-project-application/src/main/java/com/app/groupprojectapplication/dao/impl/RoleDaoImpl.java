package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IFacilityReport;
import com.app.groupprojectapplication.dao.IRoleDao;
import com.app.groupprojectapplication.domain.PersonalDocument;
import com.app.groupprojectapplication.domain.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class RoleDaoImpl implements IRoleDao {

    Role role;

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public Role getRoleById(Integer id) {
        Session session = sessionFactory.openSession();
        role = session.get(Role.class, id);
        session.close();
        return role;
    }

    @Override
    public List<Role> getRolesByLastModifiedUserId(Integer user_id) {
        Session session = sessionFactory.openSession();
        List<Role> RoleList = session.createQuery("From Role r WHERE r.user.id = " + user_id).getResultList();
        session.close();
        return RoleList;
    }

    @Override
    public void insertRole(Role role) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.save(role);
        ts.commit();
        session.close();
    }
}
