package com.example.fuel.stationOwner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.fuel.R;
import com.example.fuel.Login_SignUp_Interface;
import com.example.fuel.user.FuelStation;
import com.example.fuel.user.Homepage;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

//Station Owner Profile
public class StationProfile extends AppCompatActivity {

    ChipNavigationBar stationOwnerProfile;

// Integration of Bottom Navigation Bar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_profile);

        stationOwnerProfile = findViewById(R.id.bottom_nav);

        stationOwnerProfile.setItemSelected(R.id.profile, true);

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