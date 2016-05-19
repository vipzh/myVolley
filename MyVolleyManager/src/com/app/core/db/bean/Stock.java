package com.app.core.db.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="stock_search")
public class Stock {

    @DatabaseField(generatedId=true)
    private Integer _id;

    @DatabaseField(canBeNull=true, columnName="id")
    private String id;

    @DatabaseField(canBeNull=true, columnName="stock_code")
    private String stock_code;

    @DatabaseField(canBeNull=true, columnName="stock_exchange")
    private String stock_exchange;

    @DatabaseField(canBeNull=true, columnName="stock_name")
    private String stock_name;

    @DatabaseField(canBeNull=true, columnName="stock_pinyin")
    private String stock_pinyin;

    @DatabaseField(canBeNull=true, columnName="stock_jianpin")
    private String stock_jianpin;

    @DatabaseField(canBeNull=true, columnName="update_time")
    private String update_time;

    @DatabaseField(canBeNull=true, columnName="status")
    private String status;

    @DatabaseField(canBeNull=true, columnName="stock_type")
    private String stock_type;

    @DatabaseField(canBeNull=true, columnName="listing_date")
    private String listing_date;

    @DatabaseField(canBeNull=true, columnName="is_delisted")
    private String is_delisted;

    @DatabaseField(canBeNull=true, columnName="is_suspended")
    private String is_suspended;

    @DatabaseField(canBeNull=true, columnName="favorites")
    private String favorites;

    
    public Integer get_id() {
        return _id;
    }

    
    public void set_id(Integer _id) {
        this._id=_id;
    }

    
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id=id;
    }

    
    public String getStock_code() {
        return stock_code;
    }

    
    public void setStock_code(String stock_code) {
        this.stock_code=stock_code;
    }

    
    public String getStock_exchange() {
        return stock_exchange;
    }

    
    public void setStock_exchange(String stock_exchange) {
        this.stock_exchange=stock_exchange;
    }

    
    public String getStock_name() {
        return stock_name;
    }

    
    public void setStock_name(String stock_name) {
        this.stock_name=stock_name;
    }

    
    public String getStock_pinyin() {
        return stock_pinyin;
    }

    
    public void setStock_pinyin(String stock_pinyin) {
        this.stock_pinyin=stock_pinyin;
    }

    
    public String getStock_jianpin() {
        return stock_jianpin;
    }

    
    public void setStock_jianpin(String stock_jianpin) {
        this.stock_jianpin=stock_jianpin;
    }

    
    public String getUpdate_time() {
        return update_time;
    }

    
    public void setUpdate_time(String update_time) {
        this.update_time=update_time;
    }

    
    public String getStatus() {
        return status;
    }

    
    public void setStatus(String status) {
        this.status=status;
    }

    
    public String getStock_type() {
        return stock_type;
    }

    
    public void setStock_type(String stock_type) {
        this.stock_type=stock_type;
    }

    
    public String getListing_date() {
        return listing_date;
    }

    
    public void setListing_date(String listing_date) {
        this.listing_date=listing_date;
    }

    
    public String getIs_delisted() {
        return is_delisted;
    }

    
    public void setIs_delisted(String is_delisted) {
        this.is_delisted=is_delisted;
    }

    
    public String getIs_suspended() {
        return is_suspended;
    }

    
    public void setIs_suspended(String is_suspended) {
        this.is_suspended=is_suspended;
    }

    
    public String getFavorites() {
        return favorites;
    }

    
    public void setFavorites(String favorites) {
        this.favorites=favorites;
    }
    
    
    

}
