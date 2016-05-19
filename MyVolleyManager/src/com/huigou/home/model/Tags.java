package com.huigou.home.model;

import java.io.Serializable;


public class Tags implements Serializable{

    private static final long serialVersionUID=1L;
    
    private int tid;
    private int priority;
    private String name;
    private int count;
    
    public int getTid() {
        return tid;
    }
    
    public void setTid(int tid) {
        this.tid=tid;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public void setPriority(int priority) {
        this.priority=priority;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name=name;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count=count;
    }
    

    

}
