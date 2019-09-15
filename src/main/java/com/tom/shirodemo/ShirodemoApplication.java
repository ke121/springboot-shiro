package com.tom.shirodemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.tom")
@ServletComponentScan
@MapperScan("com.tom.shirodemo.shiro.mapper")
public class ShirodemoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ShirodemoApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(ShirodemoApplication.class, args);
    }

}
