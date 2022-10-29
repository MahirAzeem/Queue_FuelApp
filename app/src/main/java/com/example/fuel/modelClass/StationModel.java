package com.example.fuel.modelClass;


import com.google.gson.annotations.SerializedName;

public class StationModel {

    private String id;
    private String stationName;
    private String location;
    private String brand;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public StationModel(String stationName, String location, String brand) {
        this.stationName = stationName;
        this.location = location;
        this.brand = brand;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @SerializedName("body")
    private  String text;

}



