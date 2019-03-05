package com.boot.core;

import com.boot.code.HelloService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {


    private static Log logger = LogFactory.getLog(HelloController.class);

    @RequestMapping(value="/test/test01/{name}" , method = RequestMethod.GET)
    public String test(@PathVariable String name) {
        logger.info("controller类中方法的参数：" + name);
        HelloService helloService = new HelloService();
        helloService.helloService();
        return "redirect:/ceng/hello.html";
    }
}
