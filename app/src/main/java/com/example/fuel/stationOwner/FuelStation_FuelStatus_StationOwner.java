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

import com.example.fuel.Controller.FuelInterface;
import com.example.fuel.R;
import com.example.fuel.adapterClass.FuelStatusAdapter;
import com.example.fuel.modelClass.FuelModel;
import com.example.fuel.modelClass.FuelStatusModel;
import com.example.fuel.modelClass.UserModel;
import com.example.fuel.user.FuelStation;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Fuel Status Fragment to display the availability of fuel
public class FuelStation_FuelStatus_StationOwner extends Fragment {




    private FuelInterface fuelInterface;

    List<FuelModel> fuelModelList;

    String ispetrolAvailable = "";
    String issuperPetrolAvailable = "";
    String isdieselAvailable = "";
    String issuperDieselAvailable = "";

    String petrolTime = "";
    String superPetrolTime = "";
    String dieselTime = "";
    String superDieselTime = "";




    //    Implementing List View for Fuel Status
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final String[] time = new String[1];

        final String[] fuelStatusTime = new String[1];

        View v = inflater.inflate(R.layout.stationowner_fuel_status, null);

        ListView mListView = (ListView) v.findViewById(R.id.listView);





        ArrayList<FuelStatusModel> fuelTypes = new ArrayList<>();


        FuelStationHomepage activity = (FuelStationHomepage) getActivity();
        String receivedStationName = activity.getAdminData();



        ///////////////////////

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ahmedameer-001-site1.atempurl.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        fuelInterface = retrofit.create(FuelInterface.class);




        String queueStationName = "admin";
        Call<List<FuelModel>> call = fuelInterface.getFuel();
        System.out.println("inside 1111111111111----------------------");
        call.enqueue(new Callback<List<FuelModel>>() {

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

                        petrolTime = fuelModelList.get(i).getPetrolTime();
                        superPetrolTime = fuelModelList.get(i).getSuperPetrolTime();
                        dieselTime = fuelModelList.get(i).getDieselTime();
                        superDieselTime = fuelModelList.get(i).getSuperDieselTime();




                    }
                    if(ispetrolAvailable.isEmpty()){
                        ispetrolAvailable = "NO STATION";
                    }
                }

                /////////
                //Create the Fuel Types and their availability
                FuelStatusModel petrol92 = new FuelStatusModel("Petrol 92", receivedStationName, petrolTime);
                FuelStatusModel petrol95 = new FuelStatusModel("Petrol 95", issuperPetrolAvailable, superPetrolTime);
                FuelStatusModel superDiesel = new FuelStatusModel("Super Diesel", isdieselAvailable, dieselTime);
                FuelStatusModel diesel = new FuelStatusModel("Diesel", issuperDieselAvailable, superDieselTime);

                //Add Fuel types to an ArrayList

                fuelTypes.add(petrol92);
                fuelTypes.add(petrol95);
                fuelTypes.add(superDiesel);
                fuelTypes.add(diesel);


                FuelStatusAdapter adapter = new FuelStatusAdapter(getActivity(), R.layout.adapter_view_layout_fuelstatus_stationowner, fuelTypes);
                mListView.setAdapter(adapter);



                // Update Fuel Availability when ListView is clicked
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
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




                                 String petrol="";
                                 String petrolTime="";
                                 String superPetrol="Yes";
                                 String superPetrolTime="10";
                                 String diesel="";
                                 String dieselTime="";
                                 String superDiesel="";
                                 String superDieselTime="";
                                 String stationName=queueStationName;


                                FuelModel post = new FuelModel(petrol,petrolTime,superPetrol,superPetrolTime,diesel,dieselTime,superDiesel,superDieselTime,stationName);
                                Call<FuelModel> call = fuelInterface.updateFuel(queueStationName,post);
                                call.enqueue(new Callback<FuelModel>() {
                                    @Override
                                    public void onResponse(Call<FuelModel> call, Response<FuelModel> response) {
                                        System.out.println("fuel updated sucessfully");
                                    }

                                    @Override
                                    public void onFailure(Call<FuelModel> call, Throwable t) {
                                        System.out.println("fuel updated failed");
                                    }
                                });






                                alertDialog.dismiss();
                            }
                        });
                        alertDialog.show();

                    }
                });













            }

            @Override
            public void onFailure(Call<List<FuelModel>> call, Throwable t) {
                System.out.println("Fuel data retreived Failed");
            }
        });


        return v;

    }

}