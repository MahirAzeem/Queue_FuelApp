package com.example.fuel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;

import com.example.fuel.user.Homepage;
import com.example.fuel.user.UserProfile;
import com.example.fuel.userLogin.Login;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class NavigationBar extends AppCompatActivity {

    ChipNavigationBar bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);

        bottomNav = findViewById(R.id.bottom_nav);

        bottomNav.setItemSelected(R.id.homepage, true);

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {

                switch (id){
                    case R.id.homepage:
                        Intent homepage = new Intent(NavigationBar.this,Homepage.class);
                        startActivity(homepage);
                        break;
                    case R.id.profile:
                        Intent profile = new Intent(NavigationBar.this,UserProfile.class);
                        startActivity(profile);
                        break;
                    case R.id.logout:
                        Intent login = new Intent(NavigationBar.this,Login.class);
                        startActivity(login);
                        break;
                }

            }
        });
    }
}