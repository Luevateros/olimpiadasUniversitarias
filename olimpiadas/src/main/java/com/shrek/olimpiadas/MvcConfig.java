package com.shrek.olimpiadas;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path imaDir = Paths.get("./disciplina-imagenes");
        String imaPath = imaDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/disciplinas-imagenes/**").addResourceLocations("file:/home/kvsp/Desktop/CComputacion/6tosemestre/Ing Software/proyectoV2/olimpiadas/disciplinas-imagenes/");
    }
}
