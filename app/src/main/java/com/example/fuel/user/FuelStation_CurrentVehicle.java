package com.example.fuel.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.fuel.R;
import com.example.fuel.adapterClass.CurrentVehicleAdapter;
import com.example.fuel.modelClass.CurrentVehicleModel;

import java.util.ArrayList;

//    Implementing List View for to identify the count of Vehicle Types
public class FuelStation_CurrentVehicle extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_currrent_vehicle, null);

        ListView mListView = (ListView) v.findViewById(R.id.listView);

        //Create the Vehicle Types and their availability
        CurrentVehicleModel lightVehicle = new CurrentVehicleModel("Light Vehicle", 50);
        CurrentVehicleModel heavyVehicle = new CurrentVehicleModel("Heavy Vehicle", 40);
        CurrentVehicleModel bike = new CurrentVehicleModel("Bike", 30);
        CurrentVehicleModel threeWheel = new CurrentVehicleModel("Three Wheel", 20);

        //Add Vehicle types to an ArrayList
        ArrayList<CurrentVehicleModel> vehicleTypes = new ArrayList<>();
        vehicleTypes.add(lightVehicle);
        vehicleTypes.add(heavyVehicle);
        vehicleTypes.add(bike);
        vehicleTypes.add(threeWheel);

        CurrentVehicleAdapter adapter = new CurrentVehicleAdapter(getActivity(), R.layout.adapter_view_layout_currentvehicle, vehicleTypes);
        mListView.setAdapter(adapter);

        return v;
    }
}