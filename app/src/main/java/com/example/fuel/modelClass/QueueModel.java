package com.example.fuel.modelClass;

import java.time.format.DateTimeFormatter;

public class QueueModel {


    private String id;
    private String reason;
    private DateTimeFormatter arivalTime;
    private DateTimeFormatter departureTime;
    private String vehicleType;
    private String stationName;


    public QueueModel(DateTimeFormatter arivalTime, String vehicleType, String stationName) {
        this.arivalTime = arivalTime;
        this.vehicleType = vehicleType;
        this.stationName = stationName;
    }

    public QueueModel(String reason, DateTimeFormatter departureTime, String stationName) {
        this.reason = reason;
        this.departureTime = departureTime;
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

    public DateTimeFormatter getArivalTime() {
        return arivalTime;
    }

    public void setArivalTime(DateTimeFormatter arivalTime) {
        this.arivalTime = arivalTime;
    }

    public DateTimeFormatter getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(DateTimeFormatter departureTime) {
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
