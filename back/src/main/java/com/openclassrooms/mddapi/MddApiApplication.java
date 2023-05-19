package com.openclassrooms.mddapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.openclassrooms.mddapi.repositories")
@SpringBootApplication
public class MddApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MddApiApplication.class, args);
    }

}
