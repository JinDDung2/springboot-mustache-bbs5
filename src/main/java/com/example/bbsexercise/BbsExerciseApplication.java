package com.example.bbsexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BbsExerciseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BbsExerciseApplication.class, args);
    }

}
