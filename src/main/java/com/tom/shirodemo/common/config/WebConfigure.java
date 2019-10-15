package com.tom.shirodemo.common.config;

import com.tom.shirodemo.common.interceptor.ValidaterInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigure implements WebMvcConfigurer {
    @Autowired
    ValidaterInterceptor validaterInterceptor ;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(validaterInterceptor).addPathPatterns("/signtest").excludePathPatterns("/hello") ;
    }
}