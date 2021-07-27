package com.aptalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AptalkApplication {

    public static void main(String[] args) {
        SpringApplication.run(AptalkApplication.class, args);
    }

}
