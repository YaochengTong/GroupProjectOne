package com.app.groupprojectapplication.config;

import com.app.groupprojectapplication.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean<JwtFilter> jwtFilter() {
//        final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new JwtFilter());
//        registrationBean.addUrlPatterns("/*");
//
//        return registrationBean;
//    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//
//        //allow Authorization to be exposed
//        config.setExposedHeaders(Arrays.asList("token", "loginRequired"));
//        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        config.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE"));
//        source.registerCorsConfiguration("/*", config.applyPermitDefaultValues());
//        return source;
//    }
}