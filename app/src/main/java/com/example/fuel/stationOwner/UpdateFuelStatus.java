package com.example.fuel.stationOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fuel.MainActivity;
import com.example.fuel.R;
import com.example.fuel.user.FuelStation;
import com.google.android.material.textfield.TextInputLayout;

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

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                fuelStatusValue = adapterView.getItemAtPosition(position).toString();
//                Toast.makeText(getActivity(), "Item: " + item, Toast.LENGTH_SHORT).show();

//                if(fuelStatusValue != null){
//
//                    //        Pass updated status to List View
//
//                    Button updateFuelStatus = findViewById(R.id.updateFuelStatus);
//
//                    updateFuelStatus.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Intent intent = new Intent(UpdateFuelStatus.this, FuelStation.class);
//                            intent.putExtra("updatedStatus", fuelStatusValue);
//                            startActivity(intent);
//                        }
//                    });
//                }
            }
        });



//        Update Fuel Availability Status (Pass value from List View to Update Fuel Status activity)
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            fuel_Type = extras.getString("fuelType");
            fuel_Availability = extras.getString("fuelAvailability");
        }

        textView.setText(fuel_Type);
        autoCompleteTextView.setHint(fuel_Availability);

    }

}
