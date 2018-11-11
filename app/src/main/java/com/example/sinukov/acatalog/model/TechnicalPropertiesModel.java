package com.example.sinukov.acatalog.model;


import com.j256.ormlite.field.DatabaseField;

public class TechnicalPropertiesModel {

    @DatabaseField(generatedId = true, columnName = "_id")
    private int id;
    @DatabaseField(columnName = "car_id")
    private int brandId;
    @DatabaseField(columnName = "car_name")
    private String brandName;
    @DatabaseField(columnName = "modifications_id")
    private String modificationsId;
    @DatabaseField(columnName = "body_type")
    private String bodyType;
    @DatabaseField(columnName = "number_doors")
    private String numberDoors;
    @DatabaseField(columnName = "number_seats")
    private String numberSeats;
    @DatabaseField(columnName = "dimensions_length")
    private String dimensionsLength;
    @DatabaseField(columnName = "dimensions_width")
    private String dimensionsWidth;
    @DatabaseField(columnName = "dimensions_height")
    private String dimensionsHeight;
    @DatabaseField(columnName = "acceleration_time")
    private String accelerationTime;
    @DatabaseField(columnName = "top_speed")
    private String topSpeed;
    @DatabaseField(columnName = "working_volume_engine")
    private String workingVolumeEngine;
    @DatabaseField(columnName = "fuel_trass")
    private String fuelTrass;
    @DatabaseField(columnName = "fuel_city")
    private String fuelCity;
    @DatabaseField(columnName = "fuel_mixed")
    private String fuelMixed;
    @DatabaseField()
    private String transmission;

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

    public String getModificationsId() {
        return modificationsId;
    }

    public void setModificationsId(String modificationsId) {
        this.modificationsId = modificationsId;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getNumberDoors() {
        return numberDoors;
    }

    public void setNumberDoors(String numberDoors) {
        this.numberDoors = numberDoors;
    }

    public String getNumberSeats() {
        return numberSeats;
    }

    public void setNumberSeats(String numberSeats) {
        this.numberSeats = numberSeats;
    }

    public String getDimensionsLength() {
        return dimensionsLength;
    }

    public void setDimensionsLength(String dimensionsLength) {
        this.dimensionsLength = dimensionsLength;
    }

    public String getDimensionsWidth() {
        return dimensionsWidth;
    }

    public void setDimensionsWidth(String dimensionsWidth) {
        this.dimensionsWidth = dimensionsWidth;
    }

    public String getDimensionsHeight() {
        return dimensionsHeight;
    }

    public void setDimensionsHeight(String dimensionsHeight) {
        this.dimensionsHeight = dimensionsHeight;
    }

    public String getAccelerationTime() {
        return accelerationTime;
    }

    public void setAccelerationTime(String accelerationTime) {
        this.accelerationTime = accelerationTime;
    }

    public String getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(String topSpeed) {
        this.topSpeed = topSpeed;
    }

    public String getWorkingVolumeEngine() {
        return workingVolumeEngine;
    }

    public void setWorkingVolumeEngine(String workingVolumeEngine) {
        this.workingVolumeEngine = workingVolumeEngine;
    }

    public String getFuelTrass() {
        return fuelTrass;
    }

    public void setFuelTrass(String fuelTrass) {
        this.fuelTrass = fuelTrass;
    }

    public String getFuelCity() {
        return fuelCity;
    }

    public void setFuelCity(String fuelCity) {
        this.fuelCity = fuelCity;
    }

    public String getFuelMixed() {
        return fuelMixed;
    }

    public void setFuelMixed(String fuelMixed) {
        this.fuelMixed = fuelMixed;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

}