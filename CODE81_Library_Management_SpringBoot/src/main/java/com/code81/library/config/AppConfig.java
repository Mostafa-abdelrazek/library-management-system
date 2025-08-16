package com.code81.library.config;

import com.code81.library.security.ActivityLoggingFilter;
import com.code81.library.service.InitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner init(InitService initService){
        return args -> initService.initUsers();
    }

    @Bean
    @Order(1)
    public FilterRegistrationBean<ActivityLoggingFilter> loggingFilter(ActivityLoggingFilter filter){
        FilterRegistrationBean<ActivityLoggingFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(filter);
        reg.addUrlPatterns("/*");
        return reg;
    }
}
