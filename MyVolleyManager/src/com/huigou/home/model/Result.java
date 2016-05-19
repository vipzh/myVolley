package com.huigou.home.model;

import java.io.Serializable;

public class Result implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer uid;

    private String token;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid=uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token=token;
    }

}
