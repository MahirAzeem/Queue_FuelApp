package com.example.fuel.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fuel.R;
import com.example.fuel.adapterClass.FuelStationAdapter;
import com.example.fuel.modelClass.FuelStationModel;
import com.example.fuel.stationOwner.StationProfile;
import com.example.fuel.Login_SignUp_Interface;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

//Implementation of Homepage Screen with Recycler View and Search View
public class Homepage extends AppCompatActivity {

    private ArrayList<FuelStationModel> fuelStationModelArrayList;
    private FuelStationAdapter fuelStationAdapter;
    private SearchView searchView;
    private FuelStationAdapter.RecyclerViewClickListener listener;
    ChipNavigationBar bottomNav;

//    Page Navigation and Recycler View Implementation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        RecyclerView fuelStation = findViewById(R.id.idFuelStation);
        bottomNav = findViewById(R.id.bottom_nav);

        bottomNav.setItemSelected(R.id.homepage, true);

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {

                switch (id){
                    case R.id.profile:
                        Intent profile = new Intent(Homepage.this, StationProfile.class);
                        startActivity(profile);
                        break;
                    case R.id.logout:
                        Intent login = new Intent(Homepage.this, Login_SignUp_Interface.class);
                        startActivity(login);
                        break;
                }

            }
        });

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        setOnClickListener();

        fuelStationModelArrayList = new ArrayList<FuelStationModel>();
        fuelStationModelArrayList.add(new FuelStationModel("Aagaash Petrol Shed", "Kalubowila", "Ceypetco", R.drawable.petrol_shed));
        fuelStationModelArrayList.add(new FuelStationModel("Ahmed Petrol Shed", "Rajagiriya", "IOC", R.drawable.petrol_shed));
        fuelStationModelArrayList.add(new FuelStationModel("Malindu Petrol Shed", "Narahenpita", "IOC", R.drawable.petrol_shed));
        fuelStationModelArrayList.add(new FuelStationModel("Saajidh Petrol Shed", "Panadura", "Ceypetco", R.drawable.petrol_shed));
        fuelStationModelArrayList.add(new FuelStationModel("Hussain Petrol Shed", "Budhgamuwa", "IOC", R.drawable.petrol_shed));
        fuelStationModelArrayList.add(new FuelStationModel("Mahir Petrol Shed", "Dehiwala", "Ceypetco", R.drawable.petrol_shed));
        fuelStationModelArrayList.add(new FuelStationModel("Dilan Petrol Shed", "Kotikawatta", "IOC", R.drawable.petrol_shed));

        fuelStationAdapter = new FuelStationAdapter(this, fuelStationModelArrayList, listener);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        fuelStation.setLayoutManager(linearLayoutManager);
        fuelStation.setAdapter(fuelStationAdapter);
    }

//    Change Activity when Recycler View Card is pressed
    public void setOnClickListener() {
        listener = new FuelStationAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), FuelStation.class);
                intent.putExtra("fuelStation_name", fuelStationModelArrayList.get(position).getFuelStation_name());
                intent.putExtra("fuelStation_location", fuelStationModelArrayList.get(position).getFuelStation_location());
                startActivity(intent);
            }
        };
    }

//    Implementation of Search Filter using Fuel Station Location and Fuel Station Type
    private void filterList(String text) {

        ArrayList<FuelStationModel> filteredList = new ArrayList<>();
        for(FuelStationModel fuelStationModel: fuelStationModelArrayList){
            if(fuelStationModel.getFuelStation_location().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(fuelStationModel);
            }
            if(fuelStationModel.getFuelStation_type().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(fuelStationModel);
            }
        }
        
        if(filteredList.isEmpty()){
            Toast.makeText(this, "Fuel Station not found", Toast.LENGTH_SHORT).show();
        }else{
            fuelStationAdapter.setFilteredList(filteredList);
        }
    }

}
