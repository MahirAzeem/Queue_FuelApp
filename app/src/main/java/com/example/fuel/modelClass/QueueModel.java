package com.example.fuel.modelClass;

import java.time.format.DateTimeFormatter;

public class QueueModel {


    private String id;
    private String reason;
    private String arivalTime;
    private String departureTime;
    private String vehicleType;
    private String stationName;


    public QueueModel(String arivalTime, String vehicleType, String stationName) {
        this.arivalTime = arivalTime;
        this.vehicleType = vehicleType;
        this.stationName = stationName;
    }

    public QueueModel(String id,String departureTime, String reason, String stationName) {
        this.id = id;
        this.departureTime = departureTime;
        this.reason = reason;
        this.stationName = stationName;
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
