package com.example.fuel.stationOwner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.fuel.Controller.FuelInterface;
import com.example.fuel.Controller.QueueInterface;
import com.example.fuel.Controller.StationInterface;
import com.example.fuel.Controller.UserInterface;
import com.example.fuel.R;
import com.example.fuel.Login_SignUp_Interface;
import com.example.fuel.modelClass.FuelModel;
import com.example.fuel.modelClass.QueueModel;
import com.example.fuel.modelClass.StationModel;
import com.example.fuel.modelClass.UserModel;
import com.example.fuel.user.FuelStation;
import com.example.fuel.user.Homepage;
import com.example.fuel.user.UserProfile;
import com.google.android.material.textfield.TextInputLayout;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Station Owner Profile
public class StationProfile extends AppCompatActivity {


    private StationInterface stationInterface;
    private QueueInterface queueInterface;
    ChipNavigationBar stationOwnerProfile;
    String stationName;
    String stationId;
    String  userForStation = "";
    String  receivedPassword = "";
    List<StationModel> stationModelList;
    List<QueueModel> queueModelList;




    // Integration of Bottom Navigation Bar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_profile);
        stationOwnerProfile = findViewById(R.id.bottom_nav);
        stationOwnerProfile.setItemSelected(R.id.profile, true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            stationName = extras.getString("stationName");
            userForStation = extras.getString("userForStation");
            receivedPassword= extras.getString("receivedPassword");
        }


           TextInputLayout stationNameField = findViewById(R.id.stationNameField);
           TextInputLayout stationLocationField = findViewById(R.id.stationLocation);
           TextInputLayout stationTypeField = findViewById(R.id.stationTypeField);
           TextInputLayout stationPasswordField = findViewById(R.id.stationPasswordField);

            stationNameField.setHint(stationName);
            stationPasswordField.setHint(receivedPassword);



            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ahmedameer-001-site1.atempurl.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            stationInterface = retrofit.create(StationInterface.class);
            queueInterface = retrofit.create(QueueInterface.class);

             /*
            -------------------------------------------------------------------------
            RETREVING STATION DATA FROM THE DATABASE BASED ON THE STATION NAME TO UPDATE
            -------------------------------------------------------------------------
            */
            String stationName ="admin";
            Call<List<StationModel>> stationModelcall = stationInterface.getStation();
            stationModelcall.enqueue(new Callback<List<StationModel>>() {
                         @Override
                         public void onResponse(Call<List<StationModel>> call, Response<List<StationModel>> response) {
                             System.out.println("Station retreived Sucess");
                             stationModelList = response.body();
                             for(int i =0 ; i<stationModelList.size(); i++){
                                 if(stationModelList.get(i).getStationName().equals(stationName)){
                                     stationId=stationModelList.get(i).getId();
                                     stationTypeField.setHint(stationModelList.get(i).getBrand());
                                     stationLocationField.setHint(stationModelList.get(i).getLocation());
                                 }
                             }
                         }
                         @Override
                         public void onFailure(Call<List<StationModel>> call, Throwable t) {
                             System.out.println("Station retreived failed");
                         }
            });



        /*
        -------------------------------------------------------------------------
        UPDATING  STATION DATA TO  THE DATABASE BASED ON THE STATION ID
        -------------------------------------------------------------------------
        */
        String updatedLocation =stationLocationField.getEditText().getText().toString();
        String updatedBrand =stationTypeField.getEditText().getText().toString();

        StationModel staionModel = new StationModel(stationName,updatedLocation,updatedBrand);
        Call<StationModel> call = stationInterface.updateStation(stationId,staionModel);
        call.enqueue(new Callback<StationModel>() {
            @Override
            public void onResponse(Call<StationModel> call, Response<StationModel> response) {
                System.out.println("Update Success ");
            }
            @Override
            public void onFailure(Call<StationModel> call, Throwable t) {
                System.out.println("Update Failed ");
            }
        });









        stationOwnerProfile.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                switch (id) {
                    case R.id.homepage:
                        Intent homepage = new Intent(StationProfile.this, FuelStationHomepage.class);
                        homepage.putExtra("stationName", userForStation);
                        homepage.putExtra("password", receivedPassword);
                        receivedPassword= extras.getString("receivedPassword");
                        startActivity(homepage);
                        break;
                    case R.id.logout:
                        Intent login = new Intent(StationProfile.this, Login_SignUp_Interface.class);
                        startActivity(login);
                        break;
                }
            }
        });
    }
}