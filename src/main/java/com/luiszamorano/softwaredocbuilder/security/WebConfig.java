package com.luiszamorano.softwaredocbuilder.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configuración para /api/**
        registry.addMapping("/**")
                .allowedOrigins("*")
		.allowCredentials(false)
                .allowedMethods("*")
                .maxAge(3600);

        registry.addMapping("/resources/**")
                .allowedOrigins("http://34.176.124.13")
                .allowedOrigins("http://190.162.24.184")
                .allowedOrigins("http://179.61.99.95");
   }
}

