package com.example.fuel.modelClass;

//Current Vehicle Model Class
public class CurrentVehicleModel {
    private String vehicleType;
    private int vehicleCount;

//    Constructors
    public CurrentVehicleModel(String vehicleType, int vehicleCount) {
        this.vehicleType = vehicleType;
        this.vehicleCount = vehicleCount;
    }

//    Setters and Getters
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getVehicleCount() {
        return vehicleCount;
    }

    public void setVehicleCount(int vehicleCount) {
        this.vehicleCount = vehicleCount;
    }
}
