package com.example.fuel.modelClass;



import com.google.gson.annotations.SerializedName;

public class FuelModel {

    private String id;
    private String petrol;
    private String petrolTime;
    private String superPetrol;
    private String superPetrolTime;
    private String diesel;
    private String dieselTime;
    private String superDiesel;
    private String superDieselTime;
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


    public FuelModel(String petrol, String petrolTime, String superPetrol, String superPetrolTime, String diesel, String dieselTime, String superDiesel, String superDieselTime, String stationName) {
        this.petrol = petrol;
        this.petrolTime = petrolTime;
        this.superPetrol = superPetrol;
        this.superPetrolTime = superPetrolTime;
        this.diesel = diesel;
        this.dieselTime = dieselTime;
        this.superDiesel = superDiesel;
        this.superDieselTime = superDieselTime;
        this.stationName = stationName;
    }

    public String getPetrolTime() {
        return petrolTime;
    }

    public void setPetrolTime(String petrolTime) {
        this.petrolTime = petrolTime;
    }

    public String getSuperPetrolTime() {
        return superPetrolTime;
    }

    public void setSuperPetrolTime(String superPetrolTime) {
        this.superPetrolTime = superPetrolTime;
    }

    public String getDieselTime() {
        return dieselTime;
    }

    public void setDieselTime(String dieselTime) {
        this.dieselTime = dieselTime;
    }

    public String getSuperDieselTime() {
        return superDieselTime;
    }

    public void setSuperDieselTime(String superDieselTime) {
        this.superDieselTime = superDieselTime;
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



