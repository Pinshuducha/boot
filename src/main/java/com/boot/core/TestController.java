package com.boot.core;

import com.boot.utils.TestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    @Qualifier(value = "weiXinApi")
    private TestApi weiXinApi;
    @Autowired
    @Qualifier(value = "qqApi")
    private TestApi qqApi;

    @GetMapping("/hello")
    public String hello(@RequestParam String flag) {
        if ("1".equals(flag)) {
            // 我是微信
            return weiXinApi.getAppId();
        } else {
            // 我是QQ
            return qqApi.getAppId();
        }
    }
}
