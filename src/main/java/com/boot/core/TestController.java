package com.boot.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value="/hello/test01" , method = RequestMethod.GET)
    public String hello(@RequestParam String name) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("zhou", "lu");
        return operations.get("zhou");
    }
}
