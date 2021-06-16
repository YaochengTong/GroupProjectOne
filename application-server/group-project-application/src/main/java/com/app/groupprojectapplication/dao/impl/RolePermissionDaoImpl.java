package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.IRolePermissionDao;
import com.app.groupprojectapplication.domain.RolePermission;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class RolePermissionDaoImpl implements IRolePermissionDao {

    RolePermission rolePermission;

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public RolePermission getRolePermissionById(Integer id) {
        Session session = sessionFactory.openSession();
        rolePermission = session.get(RolePermission.class, id);
        session.close();
        return rolePermission;
    }

    @Override
    public List<RolePermission> getRolePermissionByPermissionId(Integer permission_id) {
        Session session = sessionFactory.openSession();
        List<RolePermission> rolePermissionList = session.createQuery("From RolePermission rp WHERE rp.permission.id = " + permission_id).getResultList();
        session.close();
        return rolePermissionList;
    }

    @Override
    public List<RolePermission> getRolePermissionByLastModifiedUserId(Integer user_id) {
        Session session = sessionFactory.openSession();
        List<RolePermission> rolePermissionList = session.createQuery("From RolePermission rp WHERE rp.user.id = " + user_id).getResultList();
        session.close();
        return rolePermissionList;
    }

    @Override
    public List<RolePermission> getRolePermissionByRoleId(Integer role_id) {
        Session session = sessionFactory.openSession();
        List<RolePermission> rolePermissionList = session.createQuery("From RolePermission rp WHERE rp.role.id = " + role_id).getResultList();
        session.close();
        return rolePermissionList;
    }

    @Override
    public void insertRolePermission(RolePermission rolePermission) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.save(rolePermission);
        ts.commit();
        session.close();
    }
}
