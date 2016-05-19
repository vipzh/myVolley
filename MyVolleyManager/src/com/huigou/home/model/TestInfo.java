package com.huigou.home.model;

import java.io.Serializable;


public class TestInfo implements Serializable{
    private static final long serialVersionUID=1L;
    private String title;
    private String test_desc;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title=title;
    }
    
    public String getTest_desc() {
        return test_desc;
    }
    
    public void setTest_desc(String test_desc) {
        this.test_desc=test_desc;
    }
    
    
}
