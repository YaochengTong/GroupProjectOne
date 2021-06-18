package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IPermissionDao;
import com.app.groupprojectapplication.domain.Permission;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class PermissonDaoImpl implements IPermissionDao {

    Permission permission;

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public Permission getPermissionById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        permission = session.get(Permission.class, id);
        return permission;
    }

    @Override
    public List<Permission> getPermissionsByLastModifiedUserId(Integer user_id) {
        Session session = sessionFactory.getCurrentSession();
        List<Permission> permissionsList = session.createQuery("From Permission p Where p.user.id = " + user_id).getResultList();
        return permissionsList;
    }

    @Override
    public void insertPermission(Permission permission) {
        Session session = sessionFactory.getCurrentSession();
        session.save(permission);
    }
}
