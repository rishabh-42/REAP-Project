package com.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
//        (exclude = { SecurityAutoConfiguration.class })
@EntityScan(basePackages = {"com.example.spring"})
@EnableJpaRepositories(basePackages = {"com.example.spring"})

public class ReapApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReapApplication.class, args);
    }

}
