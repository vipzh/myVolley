package com.huigou.home.model;

import java.util.List;



public class OkoerDetails extends BaseModel{

    private static final long serialVersionUID=1L;
    
    private Integer nid;
    private Long publish_time;
    private Long created_time;
    private Long changed_time;
    private Integer collection_count;
    private Integer like_count;
    private Integer comment_count;
    private Integer chat_count;
    private String number;
    private String title;
    private String subtitle;
    private String author;
    private String category;
    private String summary;
    private String img_uri;
    private String publisher;
    private String web_path;
    private String cover_uri;
    private String lead;
    private String unscramble;
    private String report_lead;
    private String vendor_feedback;
    private String explain;
    private String sort;
    private String notes;
    
    private List<TestInfo> test_info;
    private List<ScoringInfo> scoring_info;
    private List<Tags> tags;
    
    private List<String> grade_pc_pic_img_uri;
    private List<String> grade_mb_pic_img_uri;
    private List<String> sheet_imgs;
    private List<Integer> relateds;
    
    public Integer getNid() {
        return nid;
    }
    
    public void setNid(Integer nid) {
        this.nid=nid;
    }
    
    public Long getPublish_time() {
        return publish_time;
    }
    
    public void setPublish_time(Long publish_time) {
        this.publish_time=publish_time;
    }
    
    public Long getCreated_time() {
        return created_time;
    }
    
    public void setCreated_time(Long created_time) {
        this.created_time=created_time;
    }
    
    public Long getChanged_time() {
        return changed_time;
    }
    
    public void setChanged_time(Long changed_time) {
        this.changed_time=changed_time;
    }
    
    public Integer getCollection_count() {
        return collection_count;
    }
    
    public void setCollection_count(Integer collection_count) {
        this.collection_count=collection_count;
    }
    
    public Integer getLike_count() {
        return like_count;
    }
    
    public void setLike_count(Integer like_count) {
        this.like_count=like_count;
    }
    
    public Integer getComment_count() {
        return comment_count;
    }
    
    public void setComment_count(Integer comment_count) {
        this.comment_count=comment_count;
    }
    
    public Integer getChat_count() {
        return chat_count;
    }
    
    public void setChat_count(Integer chat_count) {
        this.chat_count=chat_count;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number=number;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title=title;
    }
    
    public String getSubtitle() {
        return subtitle;
    }
    
    public void setSubtitle(String subtitle) {
        this.subtitle=subtitle;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author=author;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category=category;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        this.summary=summary;
    }
    
    public String getImg_uri() {
        return img_uri;
    }
    
    public void setImg_uri(String img_uri) {
        this.img_uri=img_uri;
    }
    
    public String getPublisher() {
        return publisher;
    }
    
    public void setPublisher(String publisher) {
        this.publisher=publisher;
    }
    
    public String getWeb_path() {
        return web_path;
    }
    
    public void setWeb_path(String web_path) {
        this.web_path=web_path;
    }
    
    public String getCover_uri() {
        return cover_uri;
    }
    
    public void setCover_uri(String cover_uri) {
        this.cover_uri=cover_uri;
    }
    
    public String getLead() {
        return lead;
    }
    
    public void setLead(String lead) {
        this.lead=lead;
    }
    
    public String getUnscramble() {
        return unscramble;
    }
    
    public void setUnscramble(String unscramble) {
        this.unscramble=unscramble;
    }
    
    public String getReport_lead() {
        return report_lead;
    }
    
    public void setReport_lead(String report_lead) {
        this.report_lead=report_lead;
    }
    
    public String getVendor_feedback() {
        return vendor_feedback;
    }
    
    public void setVendor_feedback(String vendor_feedback) {
        this.vendor_feedback=vendor_feedback;
    }
    
    public String getExplain() {
        return explain;
    }
    
    public void setExplain(String explain) {
        this.explain=explain;
    }
    
    public String getSort() {
        return sort;
    }
    
    public void setSort(String sort) {
        this.sort=sort;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes=notes;
    }
    
    public List<TestInfo> getTest_info() {
        return test_info;
    }
    
    public void setTest_info(List<TestInfo> test_info) {
        this.test_info=test_info;
    }
    
    public List<ScoringInfo> getScoring_info() {
        return scoring_info;
    }
    
    public void setScoring_info(List<ScoringInfo> scoring_info) {
        this.scoring_info=scoring_info;
    }
    
    public List<Tags> getTags() {
        return tags;
    }
    
    public void setTags(List<Tags> tags) {
        this.tags=tags;
    }
    
    public List<String> getGrade_pc_pic_img_uri() {
        return grade_pc_pic_img_uri;
    }
    
    public void setGrade_pc_pic_img_uri(List<String> grade_pc_pic_img_uri) {
        this.grade_pc_pic_img_uri=grade_pc_pic_img_uri;
    }
    
    public List<String> getGrade_mb_pic_img_uri() {
        return grade_mb_pic_img_uri;
    }
    
    public void setGrade_mb_pic_img_uri(List<String> grade_mb_pic_img_uri) {
        this.grade_mb_pic_img_uri=grade_mb_pic_img_uri;
    }
    
    public List<String> getSheet_imgs() {
        return sheet_imgs;
    }
    
    public void setSheet_imgs(List<String> sheet_imgs) {
        this.sheet_imgs=sheet_imgs;
    }
    
    public List<Integer> getRelateds() {
        return relateds;
    }
    
    public void setRelateds(List<Integer> relateds) {
        this.relateds=relateds;
    }
   

    
    
}
