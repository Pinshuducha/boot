package com.boot.core;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value="/hello/test01" , method = RequestMethod.GET)
    public String hello(@RequestParam String name) {
        return "<<<<<<<<<<name>>>>>>>>>>>>:"+name;
    }
}
