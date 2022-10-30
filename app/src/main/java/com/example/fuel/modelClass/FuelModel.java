package com.example.fuel.modelClass;



import com.google.gson.annotations.SerializedName;

public class FuelModel {

    private String id;
    private String petrol;
    private String superPetrol;
    private String diesel;
    private String superDiesel;
    private String stationName;

    public FuelModel(String petrol, String superPetrol, String diesel, String superDiesel, String stationName ) {
        this.petrol = petrol;
        this.superPetrol = superPetrol;
        this.diesel = diesel;
        this.superDiesel = superDiesel;
        this.stationName = stationName;

    }

    //update fuel by  station constructor
    public FuelModel(String petrol, String superPetrol, String diesel, String superDiesel ) {
        this.petrol = petrol;
        this.superPetrol = superPetrol;
        this.diesel = diesel;
        this.superDiesel = superDiesel;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetrol() {
        return petrol;
    }

    public void setPetrol(String petrol) {
        this.petrol = petrol;
    }

    public String getSuperPetrol() {
        return superPetrol;
    }

    public void setSuperPetrol(String superPetrol) {
        this.superPetrol = superPetrol;
    }

    public String getDiesel() {
        return diesel;
    }

    public void setDiesel(String diesel) {
        this.diesel = diesel;
    }

    public String getSuperDiesel() {
        return superDiesel;
    }

    public void setSuperDiesel(String superDiesel) {
        this.superDiesel = superDiesel;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
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



