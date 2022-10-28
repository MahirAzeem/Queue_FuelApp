package com.example.fuel.modelClass;

import java.lang.reflect.Constructor;

//Fuel Status Model Class
public class FuelStatusModel {
    private String fuelName;
    private String fuelAvailability;
    private String fuelStatusChangeTime;

    public String getFuelStatusChangeTime() {
        return fuelStatusChangeTime;
    }

    public void setFuelStatusChangeTime(String fuelStatusChangeTime) {
        this.fuelStatusChangeTime = fuelStatusChangeTime;
    }

//    Getting Fuel Name
    public String getFuelName() {
        return fuelName;
    }

//    Setting Fuel Name
    public void setFuelName(String fuelName) {
        this.fuelName = fuelName;
    }

//    Get Fuel Availability
    public String getFuelAvailability() {
        return fuelAvailability;
    }

//    Set Fuel Availability
    public void setFuelAvailability(String fuelAvailability) {
        this.fuelAvailability = fuelAvailability;
    }

//    Constructor
    public FuelStatusModel(String fuelName, String fuelAvailability, String fuelStatusChangeTime) {
        this.fuelName = fuelName;
        this.fuelAvailability = fuelAvailability;
        this.fuelStatusChangeTime = fuelStatusChangeTime;
    }


}
