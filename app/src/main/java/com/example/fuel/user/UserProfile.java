package com.example.fuel.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fuel.Controller.FuelInterface;
import com.example.fuel.Controller.StationInterface;
import com.example.fuel.Controller.UserInterface;
import com.example.fuel.R;
import com.example.fuel.Login_SignUp_Interface;
import com.example.fuel.modelClass.StationModel;
import com.example.fuel.modelClass.UserModel;
import com.google.android.material.textfield.TextInputLayout;
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
    String userId;
    String testingphonenumber;

    String userEmail;

//    Implementation of user Nav Bar from Profile to other Activities
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setItemSelected(R.id.profile, true);


        userEmail = "Name not available";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userEmail = extras.getString("userEmail");
        }

        TextInputLayout userEmailField = findViewById(R.id.userEmailField);
        TextInputLayout userPhoneField = findViewById(R.id.userPhoneNoField);
        TextInputLayout userPasswordField = findViewById(R.id.userPasswordField);
        TextInputLayout userlicenceField = findViewById(R.id.userDrivingLicenseNo);
        Button updateButton = findViewById(R.id.update_profile);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ahmedameer-001-site1.atempurl.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        stationInterface = retrofit.create(StationInterface.class);
        userInterface = retrofit.create(UserInterface.class);
        fuelInterface = retrofit.create(FuelInterface.class);



        Call<List<UserModel>> call = userInterface.getUser();
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                userModelList = response.body();
                for(int i =0 ; i<userModelList.size(); i++){
                    if(userModelList.get(i).getEmail().equals(userEmail)){
                     userId=userModelList.get(i).getId();
                        testingphonenumber=userModelList.get(i).getPhoneNumber();
                        userEmailField.setHint(userModelList.get(i).getEmail());
                        userPhoneField.setHint(userModelList.get(i).getPhoneNumber());
                        userPasswordField.setHint(userModelList.get(i).getPassword());
                        userlicenceField.setHint(userModelList.get(i).getDrivingLicenceNo());
                    }
                }
            }
            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
            }
        });





        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedEmail=userEmailField.getEditText().getText().toString();
                String updatedNumber=userPhoneField.getEditText().getText().toString();
                String updatedPassword=userPasswordField.getEditText().getText().toString();
                String updatedLiecence=userlicenceField.getEditText().getText().toString();

                //updating the details from email
                UserModel userData = new UserModel(updatedLiecence,userEmail,updatedPassword,updatedNumber);
                Call<UserModel> call = userInterface.updateUser(userId,userData);
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        System.out.println("user updated sucessfully");
                    }
                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                    }
                });
            }
        });


        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                switch (id){
                    case R.id.homepage:
                        Intent homepage = new Intent(UserProfile.this, Homepage.class);
                        homepage.putExtra("userEmail", userEmail);
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