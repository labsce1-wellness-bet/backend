package com.lambdaschool.wellness.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Value("${DOMAIN_NAME}")
    private String domain_name;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/user/**").allowedOrigins(System.getenv("DOMAIN_NAME"), domain_name)
                .allowedMethods("GET", "POST", "DELETE", "PUT");
        registry.addMapping("/api/group/**").allowedOrigins(System.getenv("DOMAIN_NAME")).allowedMethods("GET", "POST",
                "DELETE", "PUT");
        registry.addMapping("/api/competition/**").allowedOrigins(System.getenv("DOMAIN_NAME")).allowedMethods("GET",
                "POST", "DELETE", "PUT");
        registry.addMapping("/api/competitor/**").allowedOrigins(System.getenv("DOMAIN_NAME")).allowedMethods("GET",
                "POST", "DELETE", "PUT");

        registry.addMapping("/image/**").allowedOrigins(System.getenv("DOMAIN_NAME")).allowedMethods("GET", "POST",
                "DELETE", "PUT");
    }
}