package com.boot.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

@RestController
public class TestController {

    @Autowired
    private JedisCluster jedisCluster;

    @RequestMapping(value="/hello/test01" , method = RequestMethod.GET)
    public String hello() {
        jedisCluster.set("zhou","lu");
       return jedisCluster.get("zhou");
    }
}
