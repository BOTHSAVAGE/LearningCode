package com.bothsavage.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public void demo(int arg1){
        System.out.println(arg1);
    }

    public void demo1(long arg1){
        System.out.println(arg1);
    }
}
