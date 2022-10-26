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
import com.example.fuel.MainActivity;
import com.example.fuel.R;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StationOwner_SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StationOwner_SignUpFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StationOwner_SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StationOwner_SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StationOwner_SignUpFragment newInstance(String param1, String param2) {
        StationOwner_SignUpFragment fragment = new StationOwner_SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


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

//        Initializing Dropdown values for Station Location and Station Type in Signup
        String[] LocationList = {"Dehiwala", "Kalubowila", "Ratmalana"};
        String[] TypeList = {"IOC", "Ceypetco"};
        AutoCompleteTextView stationLocation;
        AutoCompleteTextView stationType;
        ArrayAdapter<String> adapterStationLocations;
        ArrayAdapter<String> adapterStationTypes;

        View v =inflater.inflate(R.layout.fragment_sign_up2, null);

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
//                Toast.makeText(getActivity(), "Item: " + stationType, Toast.LENGTH_SHORT).show();
            }
        });

        final String[] stationTypeValue = new String[1];

        stationType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                stationTypeValue[0] = adapterView.getItemAtPosition(position).toString();
//               Toast.makeText(getActivity(), "Item: " + stationType, Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_sign_up2, container, false);


//        Login Field Initialization

        TextInputLayout stationName = v.findViewById(R.id.enterStationName);
        TextInputLayout stationEmail = v.findViewById(R.id.enterStationEmail);
        TextInputLayout password = v.findViewById(R.id.enterStationPassword);
        Button signUpButton = v.findViewById(R.id.signUpBtn);
        DBHelper DB = new DBHelper(getActivity());

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
                    Boolean registerUser = DB.insertData(station_Email, station_Email, pass, station_Location, station_Type);
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