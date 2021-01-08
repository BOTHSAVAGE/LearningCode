package com.bothsavage.bean;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component(value = "TestConfig")
@ConfigurationProperties(prefix = "test")
@Data
public class TestConfig {
    String name;
}
