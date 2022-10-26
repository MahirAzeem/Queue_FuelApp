package com.example.fuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fuel.stationOwnerLogin.StationLogin;
import com.example.fuel.userLogin.Login;
import com.example.fuel.userLogin.Register;


public class MainActivity extends AppCompatActivity {

    private Button button, btnLogin, btnStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        btnLogin = (Button) findViewById(R.id.register);
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                register();
//            }
//        });
//
//        button = (Button) findViewById(R.id.login);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                login();
//            }
//        });
//
//        btnStation = (Button) findViewById(R.id.StationView);
//        btnStation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                station();
//            }
//        });
    }

//    public void register(){
//        Intent intent = new Intent(this, Register.class);
//        startActivity(intent);
//    }
//
//    public void login(){
//        Intent intent = new Intent(this, Login.class);
//        startActivity(intent);
//    }
//
//    public void station(){
//        Intent intent = new Intent(this, StationLogin.class);
//        startActivity(intent);
//    }

}