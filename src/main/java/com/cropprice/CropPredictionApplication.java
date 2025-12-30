package com.cropprice;


import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cropprice.repository")
@EnableScheduling   // 👈 VERY IMPORTANT
public class CropPredictionApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(CropPredictionApplication.class, args);
       
    }	
}
