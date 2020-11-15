package com.example.demo;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    StringBuffer stringBuffer = new StringBuffer();
    StringBuilder stringBuilder = new StringBuilder();

    @RequestMapping(value = "/set" ,method = RequestMethod.GET)
    public Object test(@RequestParam("set") String test){
        if(StringUtils.isNotBlank(test)){
            stringBuffer.append("stringBuffer");
            stringBuilder.append("stringBuilder");
        }
        return  "set ok";
    }

    @RequestMapping(value = "/getBuffer" ,method = RequestMethod.GET)
    public Object getBuffer(){

        return  stringBuffer;
    }


    @RequestMapping(value = "/getBuilder" ,method = RequestMethod.GET)
    public Object getBuilder(){

        return  stringBuilder;
    }




}
