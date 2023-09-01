package com.hackathon;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ConfigWeb implements WebMvcConfigurer {

    @Value("${com.hackathon.uploadpath}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File path = new File(uploadPath);
        log.debug(path.getAbsolutePath());
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:"+path.getAbsolutePath())
//                    .setCachePeriod(31556926)
        ;
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**").allowedOrigins("http://localhost","https://806e-113-23-3-214.ngrok-free.app","https://elucidator0409.github.io");
    }

}