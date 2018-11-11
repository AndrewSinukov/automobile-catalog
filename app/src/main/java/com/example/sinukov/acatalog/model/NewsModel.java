package com.example.sinukov.acatalog.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class NewsModel {

    @DatabaseField(generatedId = true, columnName = "_id")
    private int id;

    @DatabaseField(columnName = "brand_id")
    private int brandId;

    @DatabaseField(columnName = "brand_name")
    private String brandName;

    @DatabaseField
    private String title;
    @DatabaseField
    private int rating;
    @DatabaseField
    private String description;
    @DatabaseField
    private String image;
    @DatabaseField(columnName = "date_created")
    private long dateCreate;
    @DatabaseField(columnName = "date_updated")
    private long dateUpdated;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(long dateCreate) {
        this.dateCreate = dateCreate;
    }

    public long getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(long dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

}