package com.boot.code;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HelloService {
    private static Log logger = LogFactory.getLog(HelloService.class);
    public void helloService() {
        logger.info("service类中的方法");
    }
}
