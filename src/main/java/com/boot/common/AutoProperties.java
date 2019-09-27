package com.boot.common;

import com.boot.utils.TestApi;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        QQProperties.class,
        WeiXinProperties.class})
public class AutoProperties {
    private final WeiXinProperties weiXinProperties;
    private final QQProperties qqProperties;

    public AutoProperties(WeiXinProperties weiXinProperties, QQProperties qqProperties) {
        this.weiXinProperties = weiXinProperties;
        this.qqProperties = qqProperties;
    }

    @Bean
    TestApi weiXinApi() {
        return new TestApi(this.weiXinProperties);
    }
    @Bean
    TestApi qqApi() {
        return new TestApi(this.qqProperties);
    }
}
