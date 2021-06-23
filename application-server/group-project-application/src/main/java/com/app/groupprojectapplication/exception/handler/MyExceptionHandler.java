package com.app.groupprojectapplication.exception.handler;

import com.app.groupprojectapplication.exception.ApplicationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value={ApplicationNotFoundException.class})
    public ResponseEntity<Map<String, Object>> handlerApplicationNotFound(ApplicationNotFoundException e){
        System.out.println("If ApplicationNotFoundException throws, it will be intercepted by this handler");
        Map<String, Object> map = new HashMap<>();
        map.put("exception", e.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<Map<String, Object>> handlerException(Exception e){
        Map<String, Object> map = new HashMap<>();
        map.put("exception", e.getMessage());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }
}

