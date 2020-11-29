package com.example.configuration_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConfigurationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationDemoApplication.class, args);
    }


    public String demo(){
        return "123";
    }

}
