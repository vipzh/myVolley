package com.huigou.home.model;

import java.io.Serializable;


public class ScoringInfo implements Serializable{

    private static final long serialVersionUID=1L;
    private String scoring_desc;
    private String scoring_title;
    
    public String getScoring_desc() {
        return scoring_desc;
    }
    
    public void setScoring_desc(String scoring_desc) {
        this.scoring_desc=scoring_desc;
    }
    
    public String getScoring_title() {
        return scoring_title;
    }
    
    public void setScoring_title(String scoring_title) {
        this.scoring_title=scoring_title;
    }
    
    
    
}
