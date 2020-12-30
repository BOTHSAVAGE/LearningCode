package com.bothsavage.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoServiceTest {
    @Autowired
    DemoService demoService;

    //测试单独四个
    @Test
    public void testDemo1(){
        demoService.demo(1);
    }


    //测试around
    @Test
    public void testDemo2(){
        demoService.demo1(1L);
    }
}