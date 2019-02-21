package com.boot.code;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = -6889928368005216265L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
