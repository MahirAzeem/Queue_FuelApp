package com.example.fuel.modelClass;

//Fuel Status Model Class
public class FuelStatusModel {
    private String fuelName;
    private String fuelAvailability;


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
    public FuelStatusModel(String fuelName, String fuelAvailability) {
        this.fuelName = fuelName;
        this.fuelAvailability = fuelAvailability;
    }
}
