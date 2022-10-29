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

import com.example.fuel.Controller.FuelInterface;
import com.example.fuel.Controller.StationInterface;
import com.example.fuel.Controller.UserInterface;
import com.example.fuel.DBHelper;
import com.example.fuel.Login_SignUp_Interface;
import com.example.fuel.R;
import com.example.fuel.modelClass.FuelModel;
import com.example.fuel.modelClass.StationModel;
import com.example.fuel.modelClass.UserModel;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//SignUp Interface Implementation
public class StationOwner_SignUpFragment extends Fragment {
    private StationInterface stationInterface ;
    private UserInterface userInterface ;
    private FuelInterface fuelInterface ;
    String  stationId = "";
    String  userIdresponse = "";


//  Initializing Dropdown values for Fuel Station Location and Type
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ahmedameer-001-site1.atempurl.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        stationInterface = retrofit.create(StationInterface.class);
        userInterface = retrofit.create(UserInterface.class);
        fuelInterface = retrofit.create(FuelInterface.class);


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

                UserModel userModel = new UserModel(station_Email, pass,"stationAdmin");
                Call<UserModel> userModelcall = userInterface.createUser(userModel);
                userModelcall.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    System.out.println(" User Created Successfully");
                    userIdresponse = response.body().getId();
                    System.out.println("  inside userid 77777 88888888888888  [0]------------- "+ userIdresponse);


                    StationModel stationModel = new StationModel(userIdresponse,station_Name, stationLocationValue[0],stationTypeValue[0]);
                    Call<StationModel> call1 = stationInterface.createStation(stationModel);
                    call1.enqueue(new Callback<StationModel>() {
                        @Override
                        public void onResponse(Call<StationModel> call, Response<StationModel> response) {
                            System.out.println(" Station Created Successfully");
                            System.out.println(response.body().getLocation());
                            stationId = response.body().getId();
                            System.out.println("  stationId[0] "+ stationId);
                        }
                        @Override
                        public void onFailure(Call<StationModel> call, Throwable t) {
                            System.out.println(" Station Created Failed");
                        }

                    });

                }
                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {
                   System.out.println(" User Created Failed");
                }
                });








                FuelModel fuelModel = new FuelModel("No", "No","No","No",stationId);
                Call<FuelModel> fuelModelcall = fuelInterface.createFuel(fuelModel);

                fuelModelcall.enqueue(new Callback<FuelModel>() {
                    @Override
                    public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                        if(!response.isSuccessful()){
                            System.out.println("not success");
                            return;
                        }
                        System.out.println(" Fuel Created Successfully");
                        FuelModel posts = response.body();



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

                    }

                    @Override
                    public void onFailure(Call<FuelModel> call, Throwable t) {
                        System.out.println(" Fuel Created Failed");

                    }


                });














            }});

        return v;

    }
}