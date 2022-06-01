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
        String uploadDir = "./disciplinas-imagenes/";
        Path uploadPath = Paths.get(uploadDir);
        String imaPath = uploadPath.toFile().getAbsolutePath();
        registry.addResourceHandler("/disciplinas-imagenes/**").addResourceLocations("file:" + imaPath + "/");
    }
}
