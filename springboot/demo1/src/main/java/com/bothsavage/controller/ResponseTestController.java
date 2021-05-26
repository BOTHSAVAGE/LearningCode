package com.bothsavage.controller;


import com.bothsavage.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResponseTestController {


    @GetMapping("/getUser")
    public User getUser(){
        User user  = new User();
        return user; 
    }
}
