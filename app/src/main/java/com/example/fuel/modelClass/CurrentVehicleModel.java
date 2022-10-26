package com.example.fuel.modelClass;

public class CurrentVehicleModel {
    private String vehicleType;
    private int vehicleCount;

    public CurrentVehicleModel(String vehicleType, int vehicleCount) {
        this.vehicleType = vehicleType;
        this.vehicleCount = vehicleCount;
    }

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
