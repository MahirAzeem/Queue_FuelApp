package com.example.fuel.modelClass;

//Fuel Station Model Class
public class FuelStationModel {


    private String fuelStation_name;
    private String fuelStation_location;
    private String fuelStation_type;
    private int fuelStation_image;


//    Constructor
    public FuelStationModel(String fuelStation_name, String fuelStation_location, String fuelStation_type, int fuelStation_image) {
        this.fuelStation_name = fuelStation_name;
        this.fuelStation_location = fuelStation_location;
        this.fuelStation_type = fuelStation_type;
        this.fuelStation_image = fuelStation_image;
    }

//Implementation of Setter and Getters
    public String getFuelStation_name() {
        return fuelStation_name;
    }

    public void setFuelStation_name(String fuelStation_name) {
        this.fuelStation_name = fuelStation_name;
    }

    public String getFuelStation_location() {
        return fuelStation_location;
    }

    public void setFuelStation_location(String fuelStation_location) {
        this.fuelStation_location = fuelStation_location;
    }

    public String getFuelStation_type() {
        return fuelStation_type;
    }

    public void setFuelStation_type(String fuelStation_type) {
        this.fuelStation_type = fuelStation_type;
    }

    public int getFuelStation_image() {
        return fuelStation_image;
    }

    public void setFuelStation_image(int fuelStation_image) {
        this.fuelStation_image = fuelStation_image;
    }
}
