//package com.example.fuel.user;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.AutoCompleteTextView;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.databinding.DataBindingUtil;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.lifecycle.Lifecycle;
//import androidx.viewpager2.adapter.FragmentStateAdapter;
//import androidx.viewpager2.widget.ViewPager2;
//
//import com.example.fuel.Controller.QueueInterface;
//import com.example.fuel.Controller.StationInterface;
//import com.example.fuel.Login_SignUp_Interface;
//import com.example.fuel.R;
//import com.example.fuel.databinding.ActivityFuelStationBinding;
//import com.example.fuel.databinding.ActivityFuelStationExitQueueBinding;
//import com.example.fuel.modelClass.QueueModel;
//import com.example.fuel.modelClass.UserModel;
//import com.example.fuel.stationOwner.FuelStationHomepage;
//import com.google.android.material.tabs.TabLayoutMediator;
//import com.google.android.material.textfield.TextInputLayout;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
////Implementation of Fuel Station Profile
//public class FuelStation_ExitQueue extends AppCompatActivity {
//
//    private ActivityFuelStationExitQueueBinding binding;
//    private FuelStation_ExitQueue fuelStation;
//    private ViewPagerAdapter adapter;
//    TextView myCustomMessage;
//    String fuelStation_name;
//
//
//    private QueueInterface queueInterface ;
//
//    //    Retrieving and Displaying Fuel Station Name and Fuel Station Location
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_fuel_station_exit_queue);
//        fuelStation = this;
//
//
////        System.out.println("admin----------------------- ");
////        Intent intent = new Intent(FuelStation.this, FuelStation_CurrentVehicle.class);
////        intent.putExtra("fuelStation_name", fuelStation_name);
////        startActivity(intent);
////
//
//     System.out.println("EXIT 1111111111111111111----------------------- ");
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://ahmedameer-001-site1.atempurl.com/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        queueInterface = retrofit.create(QueueInterface.class);
//
//
//        initView();
//
//
//        //      Retrieving Fuel Station name and location
//        myCustomMessage = (TextView) findViewById(R.id.myCustommessage);
//
//        fuelStation_name = "Name not available";
//        String fuelStation_location = "Location not available";
//
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            fuelStation_name = extras.getString("fuelStation_name");
//            fuelStation_location = extras.getString("fuelStation_location");
//        }
//
//        myCustomMessage.setText(fuelStation_name + "\n" + fuelStation_location);
//
//    }
//
//
//
//    public String getMyDataExitQueue() {
//        return "fuelStation_name";
//    }
//
//    // Initializing the TabLayout view of Fuel Status and Current Vehicle
//    private void initView() {
//        setupViewPager(binding.viewPager);
//        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
//                (tab, position) -> {
//                    tab.setText(adapter.mFragmentTitleList.get(position));
//                }).attach();
//
//        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {
//
//            TextView tv = (TextView) LayoutInflater.from(fuelStation)
//                    .inflate(R.layout.custom_tab, null);
//
//            binding.tabLayout.getTabAt(i).setCustomView(tv);
//        }
//    }
//
//    //    Connecting Fuel Status and Current Vehicle fragments to the adapter
//    private void setupViewPager(ViewPager2 viewPager) {
//        adapter = new ViewPagerAdapter(fuelStation.getSupportFragmentManager(),
//                fuelStation.getLifecycle());
//        adapter.addFragment(new FuelStation_FuelStatus(), "Fuel Status");
//        adapter.addFragment(new FuelStation_CurrentVehicle(), "Current Vehicles");
//
//
//        viewPager.setAdapter(adapter);
//        viewPager.setOffscreenPageLimit(1);
//
//    }
//
//    class ViewPagerAdapter extends FragmentStateAdapter {
//        private final List<Fragment> mFragmentList = new ArrayList<>();
//        private final List<String> mFragmentTitleList = new ArrayList<>();
//
//        public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
//            super(fragmentManager, lifecycle);
//        }
//
//        public void addFragment(Fragment fragment, String title) {
//            mFragmentList.add(fragment);
//            mFragmentTitleList.add(title);
//        }
//
//        @NonNull
//        @Override
//        public Fragment createFragment(int position) {
//            return mFragmentList.get(position);
//        }
//
//        @Override
//        public int getItemCount() {
//            return mFragmentList.size();
//        }
//    }
//
//    //    Implementation of Exist Queue Dialog Box
//    public void btn_exitModal(View view) {
//
//        //        Values for Exit Queue Dropdown in Join Queue Dialog
//        String[] exitReason = {"Pumped Fuel", "Fuel Over", "Other"};
//        ArrayAdapter<String> adapterExistReason;
//
//
//        final AlertDialog.Builder alert = new AlertDialog.Builder(FuelStation_ExitQueue.this);
//        View mView = getLayoutInflater().inflate(R.layout.exit_queue_dialog, null);
//
//        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) mView.findViewById(R.id.exitReason);
//        //        Values for Vehicle Type Dropdown in Join Queue Dialog
//        adapterExistReason = new ArrayAdapter<String>(FuelStation_ExitQueue.this, R.layout.list_item, exitReason);
//        autoCompleteTextView.setAdapter(adapterExistReason);
//
//
//        Button btn_cancel = (Button) mView.findViewById(R.id.cancel_button);
//        Button btn_okay = (Button) mView.findViewById(R.id.exitQueueButton);
//
//        alert.setView(mView);
//        final AlertDialog alertDialog = alert.create();
//        alertDialog.setCanceledOnTouchOutside(false);
//
//        btn_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
//
////        Dropdown value is stored in item variable
//        final String[] item = new String[1];
//
//        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                item[0] = adapterView.getItemAtPosition(position).toString();
//            }
//        });
//
////        Time value is stored in time variable
//        final String[] time = new String[1];
//
//        btn_okay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Printing Time
//                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
//                LocalDateTime now = LocalDateTime.now();
//
//                time[0] = dtf.format(now);
//
//                Toast.makeText(FuelStation_ExitQueue.this, "Item: " + item[0] + " Time: " + time[0], Toast.LENGTH_SHORT).show();
//
//                System.out.println("----------------------Item--------------------"+item[0]);
//                System.out.println("----------------------time--------------------"+time[0]);
//
//
//                String queueID= "635cd550387c133270c94a4f";
//
//                QueueModel queueModel = new QueueModel(queueID,time[0], item[0],"ddd");
//                Call<QueueModel> userModelcall = queueInterface.updateQueue(queueID,queueModel);
//                userModelcall.enqueue(new Callback<QueueModel>() {
//                    @Override
//                    public void onResponse(Call<QueueModel> call, Response<QueueModel> response) {
//                        System.out.println(" Queue Updated Successfully");
//
//                    }
//                    @Override
//                    public void onFailure(Call<QueueModel> call, Throwable t) {
//                        System.out.println(" Queue Updated Failed");
//                    }
//                });
//
//
//
//
//
//                alertDialog.dismiss();
//            }
//        });
//
//        alertDialog.show();
//
//    }
//
//
//
//
//
//
//
//
//
//
//}
