package com.example.fuel.modelClass;

public class FuelStatusModel {
    private String fuelName;
    private String fuelAvailability;


    public String getFuelName() {
        return fuelName;
    }

    public void setFuelName(String fuelName) {
        this.fuelName = fuelName;
    }

    public String getFuelAvailability() {
        return fuelAvailability;
    }

    public void setFuelAvailability(String fuelAvailability) {
        this.fuelAvailability = fuelAvailability;
    }

    public FuelStatusModel(String fuelName, String fuelAvailability) {
        this.fuelName = fuelName;
        this.fuelAvailability = fuelAvailability;
    }
}
