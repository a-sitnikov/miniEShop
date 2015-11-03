package com.acsent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("SpringFacetCodeInspection")
@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.acsent.repository")
//@EntityScan(basePackages = "com.acsent.model")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
