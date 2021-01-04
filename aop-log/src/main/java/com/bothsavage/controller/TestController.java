package com.bothsavage.controller;


import com.bothsavage.annotation.OperLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/{testName}")
    @OperLog(operModul = "测试模块",operType = "test",operDesc = "这个是用来测试的")
    public String test(@PathVariable String testName){
        return  "hello"+testName;
    }
}
