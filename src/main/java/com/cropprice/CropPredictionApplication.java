package com.cropprice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
})
@EnableScheduling   // 👈 VERY IMPORTANT
public class CropPredictionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CropPredictionApplication.class, args);
    }
}
