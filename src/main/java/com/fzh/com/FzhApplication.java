package com.fzh.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FzhApplication {

    public static void main(String[] args) {
        SpringApplication.run(FzhApplication.class, args);
    }

}
