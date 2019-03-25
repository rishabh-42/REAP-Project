package com.example.spring.SecurityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/home").setViewName("pages/Login");
//        registry.addViewController("/").setViewName("pages/Login");
//        registry.addViewController("/dashboard").setViewName("pages/Dashboard");
//        registry.addViewController("/login").setViewName("pages/Login");

    }

}