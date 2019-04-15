package com.boot.core;

import com.boot.code.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value="/hello/test01")
    public String hello(@RequestParam String name) {
        Person person = new Person("lisi", "New York");
        return person.toString();
    }
}
