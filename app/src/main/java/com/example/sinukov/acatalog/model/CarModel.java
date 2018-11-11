package com.example.sinukov.acatalog.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class CarModel {

    @DatabaseField(generatedId = true, columnName = "_id")
    private int id;

    @DatabaseField(columnName = "brand_id")
    private int brandId;

    @DatabaseField(columnName = "brand_name")
    private String brandName;

    @DatabaseField(columnName = "line_up_id")
    private int lineUpId;

    @DatabaseField
    private String name;

    @DatabaseField
    private String image;

    @DatabaseField(columnName = "date_created")
    private long dateCreate;

    @DatabaseField(columnName = "date_updated")
    private long dateUpdated;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getLineUpId() {
        return lineUpId;
    }

    public void setLineUpId(int lineUpId) {
        this.lineUpId = lineUpId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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