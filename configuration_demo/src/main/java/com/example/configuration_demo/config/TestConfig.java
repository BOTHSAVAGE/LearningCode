package com.example.configuration_demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "demo")
@Data
public class TestConfig {
    private  String id;
    private  String name;
}
