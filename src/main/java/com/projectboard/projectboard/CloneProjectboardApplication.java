package com.projectboard.projectboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class CloneProjectboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloneProjectboardApplication.class, args);
    }

}
