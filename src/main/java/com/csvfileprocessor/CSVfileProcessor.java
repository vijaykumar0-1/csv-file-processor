package com.csvfileprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CSVfileProcessor {

    public static void main(String[] args) {
        SpringApplication.run(CSVfileProcessor.class, args);
    }

}
