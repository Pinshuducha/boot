package com.boot.core;


import com.boot.common.JedisClusterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private JedisClusterConfig jedisClusterConfig;
    @RequestMapping(value="/hello/test01" , method = RequestMethod.GET)
    public String hello(@RequestParam String name) {
        jedisClusterConfig.getJedisCluster().set("zhou","lu");
       return jedisClusterConfig.getJedisCluster().get("zhou");
    }
}
