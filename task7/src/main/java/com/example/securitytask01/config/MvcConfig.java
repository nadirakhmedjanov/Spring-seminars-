package com.example.securitytask01.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    
    public void addViewControllers(@SuppressWarnings("null") ViewControllerRegistry registry) {
        registry.addViewController("/public-data").setViewName("public-data");
        registry.addViewController("/").setViewName("public-data");
        registry.addViewController("/private-data").setViewName("private-data");
        registry.addViewController("/login").setViewName("login");

    }
}
