package com.example.fuel.stationOwner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fuel.R;
import com.example.fuel.user.FuelStation;
import com.google.android.material.textfield.TextInputLayout;

//Edit Fuel Availability Implementation
public class UpdateFuelStatus extends AppCompatActivity {

    TextView textView;
    AutoCompleteTextView autoCompleteTextView;
    long id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_vehicle_status_dialog);



    }


}
