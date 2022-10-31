package com.example.fuel.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fuel.Controller.FuelInterface;
import com.example.fuel.Controller.StationInterface;
import com.example.fuel.R;
import com.example.fuel.adapterClass.FuelStationAdapter;
import com.example.fuel.adapterClass.FuelStopRecyclerViewAdapter;
import com.example.fuel.modelClass.FuelStationModel;
import com.example.fuel.modelClass.StationModel;

import com.example.fuel.stationOwner.StationProfile;
import com.example.fuel.Login_SignUp_Interface;
import com.example.fuel.stationOwner.recyclerviewItemClick.RecyclerViewInterface;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Implementation of Homepage Screen with Recycler View and Search View
public class Homepage extends AppCompatActivity implements RecyclerViewInterface {

    RecyclerView recyclerView;
    RecyclerViewInterface recyclerViewInterface;
    private ArrayList<FuelStationModel> fuelStationModelArrayList;
    private FuelStopRecyclerViewAdapter fuelStationAdapter;
    private SearchView searchView;
    FuelStationAdapter.RecyclerViewClickListener listener;
    ChipNavigationBar bottomNav;
    String emailfromLogin;

    List<StationModel> fuelStationModelList;
    private StationInterface stationInterface;

    //    Page Navigation and Recycler View Implementation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        recyclerViewInterface = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Context context = new Homepage();
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        recyclerView = findViewById(R.id.idFuelStation);





        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            emailfromLogin = extras.getString("userEmail");

        }






        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ahmedameer-001-site1.atempurl.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        stationInterface = retrofit.create(StationInterface.class);


        Call<List<StationModel>> call = stationInterface.getStation();

        call.enqueue(new Callback<List<StationModel>>() {

            @Override
            public void onResponse(Call<List<StationModel>> call, Response<List<StationModel>> response) {






                fuelStationModelList = response.body();



                fuelStationAdapter = new FuelStopRecyclerViewAdapter( recyclerViewInterface,context, fuelStationModelList);
//


                recyclerView.setAdapter( fuelStationAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));









                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        filterFuelStation(newText);
                        return true;
                    }
                });





            }



            @Override
            public void onFailure(Call<List<StationModel>> call, Throwable t) {

            }

        });



        bottomNav = findViewById(R.id.bottom_nav);

//        System.out.println("STATION MODEL LIST:"+ fuelStationModelList.size());

        bottomNav.setItemSelected(R.id.homepage, true);

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {

                switch (id) {
                    case R.id.profile:

                        Intent intent = new Intent(Homepage.this, UserProfile.class);
                        intent.putExtra("userEmail", emailfromLogin);
                        startActivity(intent);

//                        Intent profile = new Intent(Homepage.this, UserProfile.class);
//                        startActivity(profile);
                        break;
                    case R.id.logout:
                        Intent login = new Intent(Homepage.this, Login_SignUp_Interface.class);
                        startActivity(login);
                        break;
                }

            }
        });



//        setOnClickListener();

        fuelStationModelArrayList = new ArrayList<FuelStationModel>();




    }

    //    Implementation of Search Filter using Fuel Station Location and Fuel Station Type
    private void filterFuelStation(String newvalue) {
        List<StationModel> fuelStopslist = new ArrayList<>();
        for (StationModel fuel : fuelStationModelList) {
            if (fuel.getLocation().contains(newvalue.toLowerCase())) {
                fuelStopslist.add(fuel);
            }
        }

        if (fuelStopslist.isEmpty()) {
            Toast.makeText(this,"No item found", Toast.LENGTH_SHORT).show();
        }else{
            fuelStationAdapter.setFilteredList(fuelStopslist);
        }

    }


    //      Change Activity when Recycler View Card is pressed
//  public void setOnClickListener(FuelStationAdapter.RecyclerViewClickListener listener) {
//      listener = new FuelStationAdapter.RecyclerViewClickListener() {
//          @Override
//          public void onClick(View v, int position) {
//              Intent intent = new Intent(getApplicationContext(), FuelStation.class);
//              intent.putExtra("fuelStation_name", fuelStationModelList.get(position).getStationName());
//              intent.putExtra("fuelStation_location", fuelStationModelList.get(position).getLocation());
//              startActivity(intent);
//          }
//      };
//  }



    @Override
    public void onItemClick(int postion) {
        System.out.println("clicked here");
        Intent intent = new Intent(this, FuelStation.class);
        intent.putExtra("fuelStation_name",fuelStationModelList.get(postion).getStationName());
        intent.putExtra("fuelStation_location",fuelStationModelList.get(postion).getLocation() );
        intent.putExtra("emailfromHome",emailfromLogin );


        startActivity(intent);
    }
}
