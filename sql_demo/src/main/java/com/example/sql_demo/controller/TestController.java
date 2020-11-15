package com.example.sql_demo.controller;


import com.example.sql_demo.pojo.Facility;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {


    @RequestMapping(value = "/testGet",method = RequestMethod.GET)
    String testGet( @RequestParam("test") String test){
        return test;
    }


    @RequestMapping(value = "/testPost",method = RequestMethod.POST)
    Object testPost(@Validated @RequestBody(required = true) Facility facility,
                    BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            StringBuffer msg = new StringBuffer();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                //获取错误信息
                String errorMessage = fieldError.getDefaultMessage();
                //添加到错误消息集合内
                msg.append(fieldError.getField()+"："+errorMessage+" , ");
            }
            return msg.toString();
        }
        return facility;

    }

    @RequestMapping(value = "/testPostForm",method = RequestMethod.POST)
    Object testPostForm(Facility facility){
        return facility;
    }
}
