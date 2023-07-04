package com.openclassrooms.mddapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Mdd api application.
 */
@EnableJpaRepositories("com.openclassrooms.mddapi.repositories")
@SpringBootApplication
public class MddApiApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MddApiApplication.class, args);
    }

}
