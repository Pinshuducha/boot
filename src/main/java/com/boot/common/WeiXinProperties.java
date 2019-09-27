package com.boot.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="common.weixin")
public final class WeiXinProperties extends AbstractProperties {

}
