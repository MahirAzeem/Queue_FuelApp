package com.example.fuel;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.view.WindowManager;

// Splash Screen Implementation before Login
// Check Reference File

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configuring window to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        //Running the Splash screen and calling the next activity.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // on below line we are
                // creating a new intent
                Intent i = new Intent(SplashScreen.this, Login_SignUp_Interface.class);

                // on below line we are
                // starting a new activity.
                startActivity(i);

                // on the below line we are finishing
                // our current activity.
                finish();
            }
        }, 2000);

    }
}
