package com.example.fuel.stationOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fuel.Controller.StationInterface;
import com.example.fuel.Login_SignUp_Interface;
import com.example.fuel.R;
import com.example.fuel.databinding.ActivityFuelStationHomepageBinding;
import com.example.fuel.modelClass.StationModel;
import com.example.fuel.user.FuelStation_CurrentVehicle;
import com.example.fuel.stationOwner.FuelStation_FuelStatus_StationOwner;
import com.example.fuel.user.Homepage;
import com.example.fuel.user.UserProfile;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Implementation of Fuel Station Profile
public class FuelStationHomepage extends AppCompatActivity {

    private ActivityFuelStationHomepageBinding binding;
    private FuelStationHomepage fuelStationHomepage;
    private ViewPagerAdapter adapter;
    TextView myCustomMessage;
    String fuelStation_name;
    ChipNavigationBar bottomNav;

    private StationInterface stationInterface ;
    List<StationModel> fuelStationModelList;
    String  userForStation = "";
    String  receivedPassword = "";
    String  testName;
    String  dbStationName;
    String  dbStationLocation = "";
    String  dbStationBrand = "";
    String ReceivedfuelStation_name;


    //Retrieving and Displaying Fuel Station Name and Fuel Station Location
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fuel_station_homepage);
        fuelStationHomepage = this;
        initView();

        TextView stationName = findViewById(R.id.myCustommessage);
        TextView stationLocation = findViewById(R.id.myCustommessage2);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userForStation = extras.getString("stationName");
            receivedPassword = extras.getString("password");
        }
        System.out.println("userForStation id : "+userForStation);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ahmedameer-001-site1.atempurl.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        stationInterface = retrofit.create(StationInterface.class);

        /*
        --------------------------------------------
        RETREVING STATION DATA FROM THE DATABASE BASED ON THE STATION NAME
        ----------------------------
        */

        Call<List<StationModel>> call = stationInterface.getStation();
        call.enqueue(new Callback<List<StationModel>>() {
            @Override
            public void onResponse(Call<List<StationModel>> call, Response<List<StationModel>> response) {
                fuelStationModelList =response.body();
                for(int i=0;i<fuelStationModelList.size();i++){
                    if(fuelStationModelList.get(i).getUserId().equals(userForStation)){
                          dbStationName = response.body().get(i).getStationName();
                          dbStationLocation = response.body().get(i).getLocation();
                          dbStationBrand = response.body().get(i).getBrand();
                    }
                }
                stationName.setText(dbStationName);
                stationLocation.setText(dbStationLocation);
                testName=dbStationName;
            }
            @Override
            public void onFailure(Call<List<StationModel>> call, Throwable t) {
            }
        });

        bottomNav = findViewById(R.id.bottom_nav);

        bottomNav.setItemSelected(R.id.homepage, true);

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {

                switch (id){
                    case R.id.profile:
                        Intent profile = new Intent(FuelStationHomepage.this, StationProfile.class);
                        profile.putExtra("stationName", dbStationName);
                        profile.putExtra("userForStation", userForStation);
                        profile.putExtra("receivedPassword", receivedPassword);
                        startActivity(profile);
                        break;
                    case R.id.logout:
                        Intent login = new Intent(FuelStationHomepage.this, Login_SignUp_Interface.class);
                        startActivity(login);
                        break;
                }
            }
        });
    }

    public String getAdminData () {
        return "test station";
    }

    // Initializing the TabLayout view of Fuel Status and Current Vehicle
    private void initView() {
        setupViewPager(binding.viewPager);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> {
                    tab.setText(adapter.mFragmentTitleList.get(position));
                }).attach();

        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {

            TextView tv = (TextView) LayoutInflater.from(fuelStationHomepage)
                    .inflate(R.layout.custom_tab, null);

            binding.tabLayout.getTabAt(i).setCustomView(tv);
        }
    }

    //  Connecting Fuel Status and Current Vehicle fragments to the adapter
    private void setupViewPager(ViewPager2 viewPager) {
        adapter = new ViewPagerAdapter(fuelStationHomepage.getSupportFragmentManager(),
                fuelStationHomepage.getLifecycle());
        adapter.addFragment(new FuelStation_FuelStatus_StationOwner(), "Fuel Status");
        adapter.addFragment(new FuelStation_CurrentVehicle_StationOwner(), "Current Vehicles");

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);

    }

    class ViewPagerAdapter extends FragmentStateAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return mFragmentList.size();
        }
    }

}
