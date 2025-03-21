package com.bbs.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration
public class ThymeleafConfig {

    @Bean
    protected SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}