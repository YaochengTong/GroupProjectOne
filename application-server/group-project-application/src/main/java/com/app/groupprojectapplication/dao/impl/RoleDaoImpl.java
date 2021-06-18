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
        Session session = sessionFactory.getCurrentSession();
        role = session.get(Role.class, id);

        return role;
    }

    @Override
    public List<Role> getRolesByLastModifiedUserId(Integer user_id) {
        Session session = sessionFactory.getCurrentSession();
        List<Role> RoleList = session.createQuery("From Role r WHERE r.user.id = " + user_id).getResultList();

        return RoleList;
    }

    @Override
    public void insertRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.save(role);
    }
}
