package com.example.fuel.stationOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.fuel.R;
import com.example.fuel.adapterClass.FuelStatusAdapter;
import com.example.fuel.modelClass.FuelStatusModel;
import com.example.fuel.user.FuelStation;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;

//Fuel Status Fragment to display the availability of fuel
public class FuelStation_FuelStatus_StationOwner extends Fragment {


//    Implementing List View for Fuel Status
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final String[] time = new String[1];

        final String[] fuelStatusTime = new String[1];

        View v = inflater.inflate(R.layout.stationowner_fuel_status, null);

        ListView mListView = (ListView) v.findViewById(R.id.listView);

        //Create the Fuel Types and their availability
        FuelStatusModel petrol92 = new FuelStatusModel("Petrol 92", "Yes", "1");
        FuelStatusModel petrol95 = new FuelStatusModel("Petrol 95", "No", "1");
        FuelStatusModel superDiesel = new FuelStatusModel("Super Diesel", "Yes", "1");
        FuelStatusModel diesel = new FuelStatusModel("Diesel", "No", "1");

        //Add Fuel types to an ArrayList
        ArrayList<FuelStatusModel> fuelTypes = new ArrayList<>();
        fuelTypes.add(petrol92);
        fuelTypes.add(petrol95);
        fuelTypes.add(superDiesel);
        fuelTypes.add(diesel);



        FuelStationHomepage activity = (FuelStationHomepage) getActivity();
        String receivedStationName = activity.getAdminData();



        ///////////////////////

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ahmedameer-001-site1.atempurl.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        fuelInterface = retrofit.create(FuelInterface.class);


        FuelStatusAdapter adapter = new FuelStatusAdapter(getActivity(), R.layout.adapter_view_layout_fuelstatus_stationowner, fuelTypes);
        mListView.setAdapter(adapter);



        // Update Fuel Availability when ListView is clicked
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onResponse(Call<List<FuelModel>> call, Response<List<FuelModel>> response) {
                System.out.println("Fuel data retreived Sucessfully");
                fuelModelList = response.body();

                for(int i =0 ; i<fuelModelList.size(); i++){
                    if(fuelModelList.get(i).getStationName().equals(queueStationName)){
                        ispetrolAvailable = fuelModelList.get(i).getPetrol();
                        issuperPetrolAvailable = fuelModelList.get(i).getSuperPetrol();
                        isdieselAvailable = fuelModelList.get(i).getDiesel();
                        issuperDieselAvailable = fuelModelList.get(i).getSuperDiesel();
                    }
                    if(ispetrolAvailable.isEmpty()){
                        ispetrolAvailable = "NO STATION";
                    }
                }

                /////////
                //Create the Fuel Types and their availability
                FuelStatusModel petrol92 = new FuelStatusModel("Petrol 92", receivedStationName, "1");
                FuelStatusModel petrol95 = new FuelStatusModel("Petrol 95", issuperPetrolAvailable, "1");
                FuelStatusModel superDiesel = new FuelStatusModel("Super Diesel", isdieselAvailable, "1");
                FuelStatusModel diesel = new FuelStatusModel("Diesel", issuperDieselAvailable, "1");

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


                // Fuel Availability Dropdown
                String[] fuelStatus;

                if(fuelTypes.get(position).getFuelAvailability().equals("Yes")){
                    fuelStatus = new String[]{"No"};
                }else{
                    fuelStatus = new String[]{"Yes"};
                }

                ArrayAdapter<String> adapterFuelAvailability;


                final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.edit_vehicle_status_dialog, null);
                final TextView fuelType = (TextView) mView.findViewById(R.id.fuelType);
                fuelType.setText(fuelTypes.get(position).getFuelName());
                final AutoCompleteTextView fuelAvailabilityDropdown = (AutoCompleteTextView) mView.findViewById(R.id.fuelAvailability);
                adapterFuelAvailability = new ArrayAdapter<String>(getActivity(), R.layout.list_item, fuelStatus);
                fuelAvailabilityDropdown.setAdapter(adapterFuelAvailability);
                Button btn_cancel = (Button) mView.findViewById(R.id.cancel_button);
                Button btn_okay = (Button) mView.findViewById(R.id.updateFuelStatus);
                alert.setView(mView);
                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

//        Update Button in Update Fuel Modal

                final String[] item = new String[1];

                fuelAvailabilityDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        item[0] = adapterView.getItemAtPosition(position).toString();

                    }
                });

                btn_okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Printing Time
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
                        LocalDateTime now = LocalDateTime.now();

//                        Toast.makeText(getActivity(), "Item: " + item[0] + " Time: " + dtf.format(now), Toast.LENGTH_SHORT).show();
                        time[0] = dtf.format(now);


                        if(fuelTypes.get(position).getFuelName().equals("Petrol 92")){
//                            Toast.makeText(getActivity(), "Petrol 92: " + time[0], Toast.LENGTH_SHORT).show();
                            petrol92.setFuelStatusChangeTime(time[0]);
                            Toast.makeText(getActivity(), "Get: " + fuelTypes.get(position).getFuelStatusChangeTime(), Toast.LENGTH_SHORT).show();
                        }else if(fuelTypes.get(position).getFuelName().equals("Petrol 95")){
                            Toast.makeText(getActivity(), "Petrol 95: " + time[0], Toast.LENGTH_SHORT).show();
                            petrol95.setFuelStatusChangeTime(time[0]);
                        }else if(fuelTypes.get(position).getFuelName().equals("Super Diesel")){
                            Toast.makeText(getActivity(), "Super Diesel: " + time[0], Toast.LENGTH_SHORT).show();
                            superDiesel.setFuelStatusChangeTime(time[0]);
                        }else if(fuelTypes.get(position).getFuelName().equals("Diesel")){
                            Toast.makeText(getActivity(), "Super Diesel: " + time[0], Toast.LENGTH_SHORT).show();
                            diesel.setFuelStatusChangeTime(time[0]);
                        }

                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();

            }
        });

        return v;

    }

}