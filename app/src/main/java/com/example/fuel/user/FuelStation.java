package com.example.fuel.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fuel.R;
import com.example.fuel.databinding.ActivityFuelStationBinding;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class FuelStation extends AppCompatActivity {

    private ActivityFuelStationBinding binding;
    private FuelStation fuelStation;
    private ViewPagerAdapter adapter;
    TextView myCustomMessage;
    String fuelStation_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fuel_station);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fuel_station);
        fuelStation = this;

        initView();


        //      Retrieving Fuel Station name and location
        myCustomMessage = (TextView) findViewById(R.id.myCustommessage);

        fuelStation_name = "Namenot available";
        String fuelStation_location = "Location not available";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fuelStation_name = extras.getString("fuelStation_name");
            fuelStation_location = extras.getString("fuelStation_location");
        }

        myCustomMessage.setText(fuelStation_name + "\n" + fuelStation_location);

    }

    private void initView() {
        setupViewPager(binding.viewPager);
//        binding.Login_SignUp_Interface.setupWithViewPager(binding.viewPager);


        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> {
                    tab.setText(adapter.mFragmentTitleList.get(position));
//                tab.setCustomView(R.layout.custom_tab);
                }).attach();

        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {

            TextView tv = (TextView) LayoutInflater.from(fuelStation)
                    .inflate(R.layout.custom_tab, null);

            binding.tabLayout.getTabAt(i).setCustomView(tv);
        }
    }

    private void setupViewPager(ViewPager2 viewPager) {
        adapter = new ViewPagerAdapter(fuelStation.getSupportFragmentManager(),
                fuelStation.getLifecycle());
        adapter.addFragment(new FuelStation_FuelStatus(), "Fuel Status");
        adapter.addFragment(new FuelStation_CurrentVehicle(), "Current Vehicles");


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

    public void btn_showMessage(View view) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(FuelStation.this);
        View mView = getLayoutInflater().inflate(R.layout.join_queue_dialog, null);
        final TextInputLayout textInputLayout = (TextInputLayout) mView.findViewById(R.id.enterStationName);
        textInputLayout.setHint(fuelStation_name);
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) mView.findViewById(R.id.vehicleType);
        Button btn_cancel = (Button) mView.findViewById(R.id.cancel_button);
        Button btn_okay = (Button) mView.findViewById(R.id.join_queue);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myCustomMessage.setText(txt_inputText.getText().toString());
                alertDialog.dismiss();
            }
        });
        alertDialog.show();

//        Values for Vehicle Type Dropdown in Join Queue Dialog
        String[] vehicleType = {"Light Vehicle", "Heavy Vehicle", "Bike", "Three Wheel"};
        AutoCompleteTextView vehicle_Type;
        ArrayAdapter<String> adapterVehicleType;

        adapterVehicleType = new ArrayAdapter<String>(FuelStation.this, R.layout.list_item, vehicleType);

        autoCompleteTextView.setAdapter(adapterVehicleType);

    }

    public void btn_exitModal(View view) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(FuelStation.this);
        View mView = getLayoutInflater().inflate(R.layout.exit_queue_dialog, null);
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) mView.findViewById(R.id.exitReason);

        Button btn_cancel = (Button) mView.findViewById(R.id.cancel_button);
        Button btn_okay = (Button) mView.findViewById(R.id.exitQueueButton);

        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();
            }
        });

        alertDialog.show();

        //        Values for Exit Queue Dropdown in Join Queue Dialog
        String[] exitReason = {"Pumped Fuel", "Fuel Over", "Other"};
        AutoCompleteTextView exit_Reason;
        ArrayAdapter<String> adapterExistReason;

        //        Values for Vehicle Type Dropdown in Join Queue Dialog
        adapterExistReason = new ArrayAdapter<String>(FuelStation.this, R.layout.list_item, exitReason);

        autoCompleteTextView.setAdapter(adapterExistReason);

    }
}
