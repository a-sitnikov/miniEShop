package com.acsent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.acsent.repository")
@EntityScan(basePackages = "com.acsent.model")
public class Application extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        //SpringApplication.run(new Class<?>[] {DemoApplication.class, JpaConfig.class}, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //registry.addResourceHandler("/asserts/**").addResourceLocations("classpath:/asserts/").setCachePeriod(0);
    }
}
