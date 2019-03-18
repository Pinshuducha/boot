package com.boot.code;

import org.json.JSONObject;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = -6889928368005216265L;
    private String name;
    private String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }
}
