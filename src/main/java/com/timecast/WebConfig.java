package com.timecast;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mapeia a URL /audios/** para a pasta física uploads/audios/
        registry.addResourceHandler("/audios/**")
                .addResourceLocations("file:uploads/audios/");
    }
}