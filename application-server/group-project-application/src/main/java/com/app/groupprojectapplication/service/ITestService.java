package com.app.groupprojectapplication.service;

import java.util.Map;

public interface ITestService {
    //A interface's name should start with I. For example ITestDao instead of TestDao.
    //The implementation classes of interfaces should be created in the impl package.
    void do_something(Map<String, Object> paramMap);
}
