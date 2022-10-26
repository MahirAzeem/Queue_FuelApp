package com.example.fuel.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.fuel.R;
import com.example.fuel.Login_SignUp_Interface;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
// User Profile Class
public class UserProfile extends AppCompatActivity {

    ChipNavigationBar bottomNav;

//    Implementation of user Nav Bar from Profile to other Activities
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        bottomNav = findViewById(R.id.bottom_nav);

        bottomNav.setItemSelected(R.id.profile, true);

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