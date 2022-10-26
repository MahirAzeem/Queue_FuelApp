package com.example.fuel.stationOwnerLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fuel.R;

public class StationLogin extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_login);

        textView=(TextView) findViewById(R.id.stationRegisterTxt);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToStationRegister();
            }
        });
    }

    public void navigateToStationRegister(){
        Intent intent = new Intent(this, StationRegister.class);
        startActivity(intent);
    }
}