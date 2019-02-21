package com.boot.core;

import com.boot.code.HelloService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    private static Log logger = LogFactory.getLog(HelloController.class);
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        logger.info("controller类中方法的参数："+name);
        HelloService helloService = new HelloService();
        helloService.helloService();
        return name;
    }
}
