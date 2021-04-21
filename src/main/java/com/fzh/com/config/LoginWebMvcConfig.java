package com.fzh.com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 张小三
 * @create 2021-04-21 1:09
 * @verson 1.0.0
 */

public class LoginWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginIntercepter())
                //设置拦截的内容
                .addPathPatterns("/gymBooking/booking/**")
                .addPathPatterns("/gymBooking/welcome/**")
                .addPathPatterns("/gymBooking/venue/**")

                //放行的内容
                .excludePathPatterns("/**");
    }
}
