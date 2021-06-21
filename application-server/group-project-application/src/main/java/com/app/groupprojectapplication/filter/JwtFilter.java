package com.app.groupprojectapplication.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class JwtFilter extends OncePerRequestFilter {

    private RestTemplate restTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        ServletContext sc = httpServletRequest.getSession().getServletContext();
        AbstractApplicationContext cxt =
                (AbstractApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc);
        if(cxt != null && cxt.getBean("restClient") != null && restTemplate == null){
            restTemplate = (RestTemplate) cxt.getBean("restClient");
        }

        httpServletResponse.setHeader("Access-Control-Expose-Headers", "loginRequired");
        String JWTToken = httpServletRequest.getHeader("token");

        if(JWTToken == null || JWTToken.equals("")){
            httpServletResponse.addHeader("loginRequired", "yes");
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("token", JWTToken);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(paramMap);
        Map<String, Object> resultMap =
                restTemplate.postForObject("http://localhost:9999/auth/checkToken", request, Map.class);
        if(resultMap.get("authenticated") == null || resultMap.get("authenticated").equals("no")) {
            httpServletResponse.addHeader("loginRequired", "yes");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}