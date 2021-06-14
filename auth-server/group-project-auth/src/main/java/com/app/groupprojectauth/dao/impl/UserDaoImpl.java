package com.app.groupprojectauth.dao.impl;

import com.app.groupprojectauth.dao.IUserDao;
import com.app.groupprojectauth.domain.User;
import com.app.groupprojectauth.domain.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDaoImpl implements IUserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public User userLogin(Map<String, Object> param) {
        String sql = "SELECT " +
                "t3.id AS id, " +
                "t3.username AS username, " +
                "t3.email AS email, " +
                "t3.person_id AS person_id, " +
                "t3.create_date AS create_date, " +
                "t3.activate_flag AS activate, " +
                "t4.role_name AS role  " +
                "FROM " +
                " ( " +
                "SELECT " +
                " t1.id AS id, " +
                " t1.username AS username, " +
                " t1.PASSWORD AS PASSWORD, " +
                " t1.email AS email, " +
                " t1.create_date AS create_date, " +
                " t1.person_id AS person_id, " +
                " t2.activate_flag AS activate_flag, " +
                " t2.role_id AS role_id  " +
                "FROM " +
                " user AS t1 " +
                " LEFT JOIN user_role AS t2 ON t1.id = t2.user_id  " +
                " ) AS t3 " +
                " LEFT JOIN role AS t4 ON t3.role_id = t4.id  " +
                "WHERE " +
                " t3.username = :username  " +
                " AND t3.PASSWORD = :password";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("username", param.get("username").toString());
        parameterSource.addValue("password", param.get("password").toString());
        User user = namedParameterJdbcTemplate.queryForObject(sql, parameterSource, new UserRowMapper());
        return user;
    }
}
