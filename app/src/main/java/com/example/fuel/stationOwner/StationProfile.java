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
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Station Owner Profile
public class StationProfile extends AppCompatActivity {


    private StationInterface stationInterface ;
    private QueueInterface  queueInterface ;
    ChipNavigationBar bottomNav;
=======
    ChipNavigationBar stationOwnerProfile;


    List<StationModel> stationModelList;
    List<QueueModel> queueModelList;


    int  bikeCount = 0;
    int  threeWheelCount = 0;


// Integration of Bottom Navigation Bar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_profile);

        stationOwnerProfile = findViewById(R.id.bottom_nav);

        stationOwnerProfile.setItemSelected(R.id.profile, true);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ahmedameer-001-site1.atempurl.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        stationInterface = retrofit.create(StationInterface.class);
        queueInterface = retrofit.create(QueueInterface.class);

        System.out.println("inside   station profile ---------------------------------");


        String queueStationName ="john";

        Call<List<QueueModel>> call = queueInterface.getQueue();
        System.out.println("inside 1111111111111----------------------");
        call.enqueue(new Callback<List<QueueModel>>() {

            @Override
            public void onResponse(Call<List<QueueModel>> call, Response<List<QueueModel>> response) {
                System.out.println("inside 2222222222222----------------------");
                queueModelList = response.body();
                System.out.println("inside 4444444444----------------------");
                System.out.println(response.body().get(0).getVehicleType());
//                for(int i =0 ; i<queueModelList.size(); i++){
//
//                    if(queueModelList.get(i).getStationName().equals(queueStationName)){
//
//                        if(queueModelList.get(i).getVehicleType().equals("bike")) {
//                            bikeCount = bikeCount +1;
//
//                        }else   if(queueModelList.get(i).getVehicleType().equals("threeWheel")) {
//                            threeWheelCount = threeWheelCount +1;
//                        }
//                        System.out.println("true");
//                        System.out.println(queueModelList.size());
//                        System.out.println(queueModelList.get(i).getStationName());
//                        System.out.println(queueModelList.get(i).getVehicleType());
//
//                    }
//                }

                System.out.println("total vehicle  in station : "+queueStationName );
                System.out.println("---------------------------------");
                System.out.println("bikeCount : "+bikeCount);
                System.out.println("threeWheelCount : "+threeWheelCount);
                System.out.println("---------------------------------");


            }
            @Override
            public void onFailure(Call<List<QueueModel>> call, Throwable t) {
                System.out.println("failed .......  ---------------------");
            }
        });





//        String stationName ="test_station";
//
//
//        Call<List<StationModel>> call = stationInterface.getStation();
//        call.enqueue(new Callback<List<StationModel>>() {
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



        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
=======
        stationOwnerProfile.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {

            @Override
            public void onItemSelected(int id) {

                switch (id){
                    case R.id.homepage:
                        Intent homepage = new Intent(StationProfile.this, FuelStationHomepage.class);
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