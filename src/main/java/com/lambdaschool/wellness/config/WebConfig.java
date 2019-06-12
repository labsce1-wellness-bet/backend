package com.lambdaschool.wellness.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/user/**").allowedOrigins(System.getenv("DOMAIN_NAME"));
        registry.addMapping("/api/group/**").allowedOrigins(System.getenv("DOMAIN_NAME"));
        registry.addMapping("/image/**").allowedOrigins(System.getenv("DOMAIN_NAME"));
    }
}