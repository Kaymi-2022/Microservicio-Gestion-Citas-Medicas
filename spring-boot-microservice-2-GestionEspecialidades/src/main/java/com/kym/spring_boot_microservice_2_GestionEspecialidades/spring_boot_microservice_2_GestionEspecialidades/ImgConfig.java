package com.kym.spring_boot_microservice_2_GestionEspecialidades.spring_boot_microservice_2_GestionEspecialidades;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImgConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("imagenes").addResourceLocations("file:/C:/Repositorio/imagenes/");
    }
}
