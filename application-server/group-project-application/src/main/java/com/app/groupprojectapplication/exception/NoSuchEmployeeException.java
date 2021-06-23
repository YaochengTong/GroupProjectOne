package com.app.groupprojectapplication.exception;

public class NoSuchEmployeeException extends RuntimeException {
    private Integer userId;

    public NoSuchEmployeeException(Integer userId) {
        this.userId = userId;
    }

    public String noSuchEmployeeException(Integer userId) {
        return "No such employee with userId :" + userId;
    }
}

