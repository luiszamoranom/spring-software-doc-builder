package com.luiszamorano.softwaredocbuilder.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configuración para /api/**
        registry.addMapping("/api/**")
                .allowedOrigins("http://34.176.124.13", "http://190.162.24.184", "http://179.61.99.95")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("Content-Type", "Authorization", "X-Requested-With", "Accept")
                .allowCredentials(false)
                .maxAge(123);

        registry.addMapping("/resources/**")
                .allowedOrigins("http://34.176.124.13")
                .allowedOrigins("http://190.162.24.184")
                .allowedOrigins("http://179.61.99.95");
    }
}

