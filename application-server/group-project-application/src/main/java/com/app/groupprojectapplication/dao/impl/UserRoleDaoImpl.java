package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IUserRoleDao;
import com.app.groupprojectapplication.domain.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class UserRoleDaoImpl implements IUserRoleDao {

    UserRole userRole;

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public UserRole getUserRoleById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        userRole = session.get(UserRole.class, id);
        return userRole;
    }

    @Override
    public List<UserRole> getUserRoleByUserId(Integer user_id) {
        Session session = sessionFactory.getCurrentSession();
        List<UserRole> userRoleList = session.createQuery("From UserRole ur WHERE ur.user.id = " + user_id).getResultList();
        return userRoleList;
    }

    @Override
    public List<UserRole> getUserRoleByRoleId(Integer roleId) {
        Session session = sessionFactory.getCurrentSession();
        List<UserRole> userRoleList = session.createQuery("From UserRole ur WHERE ur.role.id = " + roleId).getResultList();
        return userRoleList;
    }


    @Override
    @Transactional
    public void insertUserRole(UserRole userRole) {
        Session session = sessionFactory.getCurrentSession();
        session.save(userRole);
    }
}
