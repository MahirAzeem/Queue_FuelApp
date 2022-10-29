package com.example.fuel.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.fuel.Controller.FuelInterface;
import com.example.fuel.Controller.StationInterface;
import com.example.fuel.Controller.UserInterface;
import com.example.fuel.R;
import com.example.fuel.Login_SignUp_Interface;
import com.example.fuel.modelClass.StationModel;
import com.example.fuel.modelClass.UserModel;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// User Profile Class
public class UserProfile extends AppCompatActivity {

    ChipNavigationBar bottomNav;
    private StationInterface stationInterface ;
    private UserInterface userInterface ;
    private FuelInterface fuelInterface ;
    List<UserModel> userModelList;

//    Implementation of user Nav Bar from Profile to other Activities
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        bottomNav = findViewById(R.id.bottom_nav);

        bottomNav.setItemSelected(R.id.profile, true);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ahmedameer-001-site1.atempurl.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        stationInterface = retrofit.create(StationInterface.class);
        userInterface = retrofit.create(UserInterface.class);
        fuelInterface = retrofit.create(FuelInterface.class);

        String userEmail ="ahm@gmail.com";


        Call<List<UserModel>> call = userInterface.getUser();
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {

                userModelList = response.body();
                for(int i =0 ; i<userModelList.size(); i++){
                    if(userModelList.get(i).getUserRole().equals(userEmail)){
                        System.out.println("true");
                        System.out.println(userModelList.size());
                        System.out.println(userModelList.get(i).getUserRole());
                        System.out.println(userModelList.get(i).getEmail());
                        System.out.println(userModelList.get(i).getDrivingLicenceNo());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

            }


        });











        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {

                switch (id){
                    case R.id.homepage:
                        Intent homepage = new Intent(UserProfile.this, Homepage.class);
                        startActivity(homepage);
                        break;
                    case R.id.logout:
                        Intent login = new Intent(UserProfile.this, Login_SignUp_Interface.class);
                        startActivity(login);
                        break;
                }

            }
        });
    }
}