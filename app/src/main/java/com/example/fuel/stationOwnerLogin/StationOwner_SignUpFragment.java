package com.example.fuel.stationOwnerLogin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.fuel.DBHelper;
import com.example.fuel.Login_SignUp_Interface;
import com.example.fuel.R;
import com.google.android.material.textfield.TextInputLayout;

//SignUp Interface Implementation
public class StationOwner_SignUpFragment extends Fragment {

//  Initializing Dropdown values for Fuel Station Location and Type
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        Initializing Dropdown values for Station Location and Station Type in Signup
        String[] LocationList = {"Colombo", "Galle", "Gampaha", "Jaffna", "Kandy"};
        String[] TypeList = {"IOC", "Ceypetco"};
        AutoCompleteTextView stationLocation;
        AutoCompleteTextView stationType;
        ArrayAdapter<String> adapterStationLocations;
        ArrayAdapter<String> adapterStationTypes;

        View v =inflater.inflate(R.layout.stationowner_sign_up, null);

        stationLocation = v.findViewById(R.id.stationLocations);
        stationType = v.findViewById(R.id.stationType);

        adapterStationLocations = new ArrayAdapter<String>(getActivity(), R.layout.list_item, LocationList);
        adapterStationTypes = new ArrayAdapter<String>(getActivity(), R.layout.list_item, TypeList);

        stationLocation.setAdapter(adapterStationLocations);
        stationType.setAdapter(adapterStationTypes);


        final String[] stationLocationValue = new String[1];

        stationLocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                stationLocationValue[0] = adapterView.getItemAtPosition(position).toString();
            }
        });

        final String[] stationTypeValue = new String[1];

        stationType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                stationTypeValue[0] = adapterView.getItemAtPosition(position).toString();
            }
        });


//        Login Field Initialization

        TextInputLayout stationName = v.findViewById(R.id.enterStationName);
        TextInputLayout stationEmail = v.findViewById(R.id.enterStationEmail);
        TextInputLayout password = v.findViewById(R.id.enterStationPassword);
        Button signUpButton = v.findViewById(R.id.signUpBtn);
        DBHelper DB = new DBHelper(getActivity());


//        Station Owner Registration Implementation
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String station_Name = stationName.getEditText().getText().toString();
                String station_Email = stationEmail.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();
                String station_Location = String.valueOf(stationLocationValue);
                String station_Type = String.valueOf(stationTypeValue);

                Boolean checkExistingUser = DB.checkExistingUser(station_Email, pass);

                if (station_Name.equals("") || pass.equals("") || station_Email.equals("") || station_Location.equals("") || station_Type.equals("")) {
                    Toast.makeText(getActivity(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else if (checkExistingUser == false) {
                    Boolean registerUser = DB.insertStationOwner(station_Email, pass);
                    if (registerUser == true) {
                        Toast.makeText(getActivity(), "You have successfully registered", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(getActivity(), Login_SignUp_Interface.class);
                        startActivity(moveToLogin);
                    } else {
                        Toast.makeText(getActivity(), "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "User already exists!", Toast.LENGTH_SHORT).show();
                }
            }});

        return v;

    }
}