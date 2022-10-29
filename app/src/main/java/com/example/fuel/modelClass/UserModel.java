package com.example.fuel.modelClass;

import com.google.gson.annotations.SerializedName;

       import com.google.gson.annotations.SerializedName;

public class UserModel {

    private String id;
    private String email;
    private String password;
    private String phoneNumber;
    private String userRole;
    private String drivingLicenceNo;


    public UserModel(String email, String password, String phoneNumber, String userRole, String drivingLicenceNo) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
        this.drivingLicenceNo = drivingLicenceNo;
    }


    public UserModel(String email, String password, String userRole) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getDrivingLicenceNo() {
        return drivingLicenceNo;
    }

    public void setDrivingLicenceNo(String drivingLicenceNo) {
        this.drivingLicenceNo = drivingLicenceNo;
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



