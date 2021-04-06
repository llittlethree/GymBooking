package com.fzh.com.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 张小三
 * @create 2021-03-14 3:32
 * @verson 1.0.0
 */
@Component
public class  GlobalCORSConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST","GET")
                .allowCredentials(false)
                .maxAge(30);
    }
}
