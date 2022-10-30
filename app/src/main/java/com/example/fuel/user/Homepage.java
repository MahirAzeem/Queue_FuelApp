package com.example.fuel.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuel.Controller.FuelInterface;
import com.example.fuel.Controller.StationInterface;
import com.example.fuel.R;
import com.example.fuel.SignInFragment;
import com.example.fuel.adapterClass.FuelStationAdapter;
import com.example.fuel.modelClass.FuelStationModel;
import com.example.fuel.modelClass.StationModel;
import com.example.fuel.stationOwner.StationProfile;
import com.example.fuel.Login_SignUp_Interface;
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
public class Homepage extends AppCompatActivity {

    private ArrayList<FuelStationModel> fuelStationModelArrayList;
    private FuelStationAdapter fuelStationAdapter;
    private SearchView searchView;
    private FuelStationAdapter.RecyclerViewClickListener listener;
    ChipNavigationBar bottomNav;

    String userEmail;


    SignInFragment  sss = new SignInFragment();

    List<StationModel> fuelStationModelList;
    private StationInterface stationInterface ;

    //    Page Navigation and Recycler View Implementation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

//        myCustomMessage = (TextView) findViewById(R.id.myCustommessage);

//       String  fuelStation_name = "Name not available";
//        String fuelStation_location = "Location not available";
//
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            fuelStation_name = extras.getString("email");
//
//        }


        userEmail = "Name not available";
//        String fuelStation_location = "Location not available";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userEmail = extras.getString("userEmail");
        }

//        myCustomMessage.setText(fuelStation_name + "\n" + fuelStation_location);
        System.out.println("home ----------------------------------"+userEmail);
        ////

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ahmedameer-001-site1.atempurl.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        stationInterface = retrofit.create(StationInterface.class);


        Call<List<StationModel>> call = stationInterface.getStation();
        call.enqueue(new Callback<List<StationModel>>() {

            @Override
            public void onResponse(Call<List<StationModel>> call, Response<List<StationModel>> response) {

                if(!response.isSuccessful()){
//                    Toast.makeText(getActivity(), "failed response", Toast.LENGTH_SHORT).show();
                    System.out.println("faillllllllllllllll");
                }

                fuelStationModelList =response.body();

                System.out.println(response.body().size());
                System.out.println(response.body().get(0).getStationName());
                System.out.println(response.body().get(1).getLocation() );
                System.out.println(response.body().get(1).getBrand() );
            }

            @Override
            public void onFailure(Call<List<StationModel>> call, Throwable t) {

            }

//            @Override
//            public void onResponse(Call<List<FuelStationModel>> call, Response<List<FuelStationModel>> response) {
//                if(!response.isSuccessful()){
////                    Toast.makeText(getActivity(), "failed response", Toast.LENGTH_SHORT).show();
//                    System.out.println("faillllllllllllllll");
//                }
//                fuelStationModelList =response.body();
//
//
//                System.out.println(response.body().size());
//                System.out.println(response.body().get(1).getStationName());
//                System.out.println(response.body().get(1).getLocation() );
//                System.out.println(response.body().get(1).getBrand() );
//
//
//                //attach adapter
//                System.out.println(fuelStationModelList.isEmpty());
//                System.out.println(fuelStationModelList.size());
//
//
//                System.out.println("inside adapter class attaching");
//                System.out.println("inside adapter class attaching----");
//                fuelStationAdapter = new FuelStationAdapter( fuelStationModelList);
//                fuelStation.setAdapter(fuelStationAdapter);
//                fuelStation.setLayoutManager(new LinearLayoutManager(SysConfig.context));
//                System.out.println("inside adapter class attaching 11111111111 : "+fuelStationModelList.size());
//
//                // below line is for setting a layout manager for our recycler view.
//                // here we are creating vertical list so we will provide orientation as vertical
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<FuelStationModel>> call, Throwable t) {
//
//            }




//            @Override
//            public void onFailure(Call<List<FuelStop>> call, Throwable t) {
//
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                System.out.println(t.getMessage());
//            }
        });





        RecyclerView fuelStation = findViewById(R.id.idFuelStation);
        bottomNav = findViewById(R.id.bottom_nav);

        bottomNav.setItemSelected(R.id.homepage, true);

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {

                switch (id){
                    case R.id.profile:
                        Intent profile = new Intent(Homepage.this, UserProfile.class);
                        profile.putExtra("userEmail", userEmail);
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
        fuelStationModelArrayList.add(new FuelStationModel("john station", "Kalubowila", "Ceypetco", R.drawable.petrol_shed));
        fuelStationModelArrayList.add(new FuelStationModel("Ahmed", "Rajagiriya", "IOC", R.drawable.petrol_shed));
//        fuelStationModelArrayList.add(new FuelStationModel("Malindu", "Narahenpita", "IOC", R.drawable.petrol_shed));
//        fuelStationModelArrayList.add(new FuelStationModel("Saajid", "Panadura", "Ceypetco", R.drawable.petrol_shed));
//        fuelStationModelArrayList.add(new FuelStationModel("Hussain", "Budhgamuwa", "IOC", R.drawable.petrol_shed));
//        fuelStationModelArrayList.add(new FuelStationModel("Mahir", "Dehiwala", "Ceypetco", R.drawable.petrol_shed));
//        fuelStationModelArrayList.add(new FuelStationModel("Dilan", "Kotikawatta", "IOC", R.drawable.petrol_shed));

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
