package com.boot.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zhoulu
 * 定时任务类
 */
@Component
public class TimerTest {
    @Value("${common.switchValue1}")
    private String switchValue1;
    @Value("${common.switchValue2}")
    private String switchValue2;

    @Scheduled(fixedDelay = 1000)
    public void timer1() {
        if (Boolean.parseBoolean(switchValue1)) {
            System.out.println("111");
        }
    }

    @Scheduled(fixedDelay = 2001)
    public void timer2() {
        if (Boolean.parseBoolean(switchValue2)) {
            System.out.println("222");
        }
    }
}
