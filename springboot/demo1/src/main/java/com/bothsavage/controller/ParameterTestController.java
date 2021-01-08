package com.bothsavage.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ParameterTestController {


    @RequestMapping("/test1/{id}/name/{name}/hehe")
    public Map<String,Object> testPathVariable(@PathVariable(value = "id") String id,
                                               @PathVariable(value = "name") String name,
                                               @PathVariable Map<String,String> kv,
                                               @RequestHeader(value = "User-Agent") String useragent,
                                               @RequestHeader Map<String,String> headers ,
                                               @RequestParam Map<String,String> params,
                                               Cookie cookies
                                               ){

         Map<String,Object> map = new HashMap<>();
         map.put("id_p",id);
         map.put("name_p",name);
         map.put("map",kv);
         map.put("useragent",useragent);
         map.put("headers",headers);
         map.put("params",params);
         map.put("cookies",cookies);

         return  map;

    }
}
