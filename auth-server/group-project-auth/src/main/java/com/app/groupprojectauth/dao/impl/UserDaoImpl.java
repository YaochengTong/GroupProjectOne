package com.app.groupprojectauth.dao.impl;

import com.app.groupprojectauth.dao.IUserDao;
import com.app.groupprojectauth.domain.Token;
import com.app.groupprojectauth.domain.User;
import com.app.groupprojectauth.domain.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements IUserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
                " (t3.username = :username or t3.email = :email) " +
                " AND t3.PASSWORD = :password";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String username = param.get("username") == null? "":param.get("username").toString();
        parameterSource.addValue("username", username);
        String email = param.get("email") == null? "":param.get("email").toString();
        parameterSource.addValue("email", email);
        parameterSource.addValue("password", param.get("password").toString());
        try {
            User user = namedParameterJdbcTemplate.queryForObject(sql, parameterSource, new UserRowMapper());
            return user;
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<String, Object> userRegister(Map<String, Object> param) {
        Map<String, Object> resultMap = new HashMap<>();
        String sql = "select id from user where username=?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,
                new Object[]{param.get("username").toString()});

        if(rows.size() > 0){
            resultMap.put("success", false);
            resultMap.put("reason", "username has exist");
            return resultMap;
        }

//        sql = "select id from person where id=?";
//        rows = jdbcTemplate.queryForList(sql,
//                new Object[]{param.get("person_id").toString()});
//        if(rows.size() == 0){
//            resultMap.put("success", false);
//            resultMap.put("reason", "person does not exist");
//            return resultMap;
//        }

        sql = "insert into user (username, email, password, person_id, create_date, modification_date)"
                + " values (?, ?, ?, NULL, ?, ?)";
        jdbcTemplate.update(sql, new Object[]{
                param.get("username"),
                param.get("email"),
                param.get("password"),
                //param.get("person_id"),
                new java.util.Date(),
                new java.util.Date()
        });
        resultMap.put("success", true);
        return resultMap;
    }

    @Override
    public Map<String, Object> getToken(Map<String, Object> param) {
        Map<String, Object> resultMap = new HashMap<>();
        String sql = "select id, token, valid_until, email, creation_employee_id from " +
                "registration_token where token=?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,
                new Object[]{param.get("token").toString()});
        if(rows.size() != 1){
            resultMap.put("success", false);
            resultMap.put("reason", "Token not unique");
        }
        Token token = new Token();
        token.setId((Integer) rows.get(0).get("id"));
        token.setEmail(rows.get(0).get("email").toString());
        token.setContent(rows.get(0).get("token").toString());
        Timestamp timestamp = (Timestamp) rows.get(0).get("valid_until");
        token.setValidUntil(Date.valueOf(timestamp.toLocalDateTime().toLocalDate()));
        token.setCreationEmployeeId((Integer) rows.get(0).get("creation_employee_id"));
        resultMap.put("success", true);
        resultMap.put("token", token);
        return resultMap;
    }
}
