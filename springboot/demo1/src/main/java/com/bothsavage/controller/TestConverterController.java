package com.bothsavage.controller;


import com.bothsavage.bean.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConverterController {


    @PostMapping("/testConverter")
    public   User TestConverter(User user){
        return user;
    }
}
