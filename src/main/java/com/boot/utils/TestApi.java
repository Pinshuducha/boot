package com.boot.utils;

import com.boot.common.AbstractProperties;

public final class TestApi {
    private final AbstractProperties properties;

    public TestApi(AbstractProperties properties) {
        this.properties = properties;
    }
    public String getAppId() {
        return properties.getAppId();
    }
}
