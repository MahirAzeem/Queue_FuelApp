package com.example.fuel.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fuel.R;
import com.example.fuel.adapterClass.CurrentVehicleAdapter;
import com.example.fuel.adapterClass.FuelStatusAdapter;
import com.example.fuel.modelClass.CurrentVehicleModel;
import com.example.fuel.modelClass.FuelStatusModel;
import com.example.fuel.stationOwner.UpdateFuelStatus;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FuelStation_CurrentVehicle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FuelStation_CurrentVehicle extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FuelStation_CurrentVehicle() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FuelStation_CurrentVehicle.
     */
    // TODO: Rename and change types and number of parameters
    public static FuelStation_CurrentVehicle newInstance(String param1, String param2) {
        FuelStation_CurrentVehicle fragment = new FuelStation_CurrentVehicle();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one, null);

        ListView mListView = (ListView) v.findViewById(R.id.listView);

        //Create the Fuel Types and their availability
        CurrentVehicleModel lightVehicle = new CurrentVehicleModel("Light Vehicle", 50);
        CurrentVehicleModel heavyVehicle = new CurrentVehicleModel("Heavy Vehicle", 40);
        CurrentVehicleModel bike = new CurrentVehicleModel("Bike", 30);
        CurrentVehicleModel threeWheel = new CurrentVehicleModel("Three Wheel", 20);

        //Add Fuel types to an ArrayList
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