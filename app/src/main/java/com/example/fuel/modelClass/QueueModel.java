package com.example.fuel.modelClass;

import java.time.format.DateTimeFormatter;

public class QueueModel {


    private String id;
    private String reason;
    private String email;
    private String arivalTime;
    private String departureTime;
    private String vehicleType;
    private String stationName;


    //create a queue
    public QueueModel(String email,String arivalTime,String departureTime,  String vehicleType, String stationName) {
        this.email = email;
        this.arivalTime = arivalTime;
        this.departureTime = departureTime;
        this.vehicleType = vehicleType;
        this.stationName = stationName;
    }

    public QueueModel(String departureTime, String reason, String stationName) {

        this.departureTime = departureTime;
        this.reason = reason;
        this.stationName = stationName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getArivalTime() {
        return arivalTime;
    }

    public void setArivalTime(String arivalTime) {
        this.arivalTime = arivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
