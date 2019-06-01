package com.boot.core;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value="/order/test")
    public String test(@RequestParam String name) {
        return name;
    }
    @GetMapping(value="/order/test01")
    public String test01(@RequestParam String name) {
        return name;
    }
}
