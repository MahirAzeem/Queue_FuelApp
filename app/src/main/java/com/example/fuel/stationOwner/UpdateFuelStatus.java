package com.example.fuel.stationOwner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fuel.R;

//Edit Fuel Availability Implementation
public class UpdateFuelStatus extends AppCompatActivity {

    TextView textView;
    AutoCompleteTextView autoCompleteTextView;
    long id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_vehicle_status_dialog);

        textView = (TextView)findViewById(R.id.fuelType);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.fuelAvailability);

        String fuel_Type = "Type not available";
        String fuel_Availability = "Availability unknown";

        // Fuel Availability Dropdown
        String[] fuelStatus = {"Yes", "No"};
        AutoCompleteTextView fuelAvailability;
        ArrayAdapter<String> adapterFuelAvailability;

        fuelAvailability = findViewById(R.id.fuelAvailability);

        adapterFuelAvailability = new ArrayAdapter<String>(this, R.layout.list_item, fuelStatus);

        fuelAvailability.setAdapter(adapterFuelAvailability);

        fuelAvailability.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public String fuelStatusValue;

//            Identifying the Fuel Status Dropdown value
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                fuelStatusValue = adapterView.getItemAtPosition(position).toString();
            }
        });


//       Update Fuel Availability Status (Pass value from List View to Update Fuel Status activity)
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            fuel_Type = extras.getString("fuelType");
            fuel_Availability = extras.getString("fuelAvailability");
        }

        textView.setText(fuel_Type);
        autoCompleteTextView.setHint(fuel_Availability);

    }

}
