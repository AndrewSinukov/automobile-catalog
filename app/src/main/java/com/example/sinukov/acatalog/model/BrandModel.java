package com.example.sinukov.acatalog.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class BrandModel {

    @DatabaseField(generatedId = true, columnName = "_id")
    private int id;

    @DatabaseField
    private String name;
    @DatabaseField

    private String path;
    @DatabaseField
    private String image;
    @DatabaseField(columnName = "date_created")
    private long dateCreate;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}