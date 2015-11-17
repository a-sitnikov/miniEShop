package com.acsent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocialConfig {

    @Bean
    public FacebookConnectInterceptor facebookConnectInterceptor(){
        return new FacebookConnectInterceptor();
    }

}
