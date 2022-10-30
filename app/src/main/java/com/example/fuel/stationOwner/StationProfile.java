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
    String  userForStation = "";
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
        }
        System.out.println("stationName profile ----------------------- :"+stationName);



            TextInputLayout stationNameField = findViewById(R.id.stationNameField);

        stationNameField.setHint(stationName);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ahmedameer-001-site1.atempurl.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        stationInterface = retrofit.create(StationInterface.class);
        queueInterface = retrofit.create(QueueInterface.class);

        System.out.println("inside   station profile ---------------------------------");






        //retreiving  the details from station
//        String stationName ="test_station";
//        Call<List<StationModel>> stationModelcall = stationInterface.getStation();
//        stationModelcall.enqueue(new Callback<List<StationModel>>() {
//                         @Override
//                         public void onResponse(Call<List<StationModel>> call, Response<List<StationModel>> response) {
//                             stationModelList = response.body();
//                             for(int i =0 ; i<stationModelList.size(); i++){
//                                 if(stationModelList.get(i).getStationName().equals(stationName)){
//                                     System.out.println("true");
//                                     System.out.println(stationModelList.size());
//                                     System.out.println(stationModelList.get(i).getStationName());
//                                     System.out.println(stationModelList.get(i).getBrand());
//                                     System.out.println(stationModelList.get(i).getLocation());
//                                 }
//                             }
//                         }
//                         @Override
//                         public void onFailure(Call<List<StationModel>> call, Throwable t) {
//                         }
//                     });




        //updating the details from station id
        StationModel staionModel = new StationModel("TEST","TEST","TEST");
        Call<StationModel> call = stationInterface.updateStation("635565f94c7f49a28a559cb3",staionModel);
        call.enqueue(new Callback<StationModel>() {
            @Override
            public void onResponse(Call<StationModel> call, Response<StationModel> response) {
                System.out.println("successs update -------");
            }

            @Override
            public void onFailure(Call<StationModel> call, Throwable t) {
            }
        });









        stationOwnerProfile.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {

            @Override
            public void onItemSelected(int id) {

                switch (id) {
                    case R.id.homepage:

                        Intent homepage = new Intent(StationProfile.this, FuelStationHomepage.class);

                        homepage.putExtra("stationName", userForStation);
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