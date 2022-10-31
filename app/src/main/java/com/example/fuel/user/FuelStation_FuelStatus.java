package com.example.fuel.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.fuel.Controller.FuelInterface;
import com.example.fuel.Controller.QueueInterface;
import com.example.fuel.Controller.StationInterface;
import com.example.fuel.R;
import com.example.fuel.adapterClass.CurrentVehicleAdapter;
import com.example.fuel.adapterClass.FuelStatusAdapter;
import com.example.fuel.modelClass.CurrentVehicleModel;
import com.example.fuel.modelClass.FuelModel;
import com.example.fuel.modelClass.FuelStatusModel;
import com.example.fuel.modelClass.QueueModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Fuel Status Fragment to display the availability of fuel
public class FuelStation_FuelStatus extends Fragment {

    private StationInterface stationInterface;
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
        View v = inflater.inflate(R.layout.user_fuel_status, null);

        ListView mListView = (ListView) v.findViewById(R.id.listView);

//
//        FuelStation_ExitQueue activity = (FuelStation_ExitQueue) getActivity();
//        String receivedFuelStationName = activity.getMyData();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ahmedameer-001-site1.atempurl.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        fuelInterface = retrofit.create(FuelInterface.class);

//        String queueStationName = receivedFuelStationName;
        String queueStationName = "Ahmed";
        Call<List<FuelModel>> call = fuelInterface.getFuel();
        call.enqueue(new Callback<List<FuelModel>>() {
            @Override
            public void onResponse(Call<List<FuelModel>> call, Response<List<FuelModel>> response) {
                System.out.println("Fuel data retreived Sucessfully");
                fuelModelList = response.body();
                System.out.println("Fuel data retreived 11111");
                System.out.println("Fuel data retreived 222222222222"+fuelModelList.size());
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

                //Create the Fuel Types and their availability
         

                FuelStatusModel petrol92 = new FuelStatusModel("Petrol 92", ispetrolAvailable, petrolTime);
                FuelStatusModel petrol95 = new FuelStatusModel("Petrol 95", issuperPetrolAvailable, superPetrolTime);
                FuelStatusModel superDiesel = new FuelStatusModel("Super Diesel", isdieselAvailable, dieselTime);
                FuelStatusModel diesel = new FuelStatusModel("Diesel", issuperDieselAvailable, superDieselTime);

                //Add Fuel types to an ArrayList
                ArrayList<FuelStatusModel> fuelTypes = new ArrayList<>();
                fuelTypes.add(petrol92);
                fuelTypes.add(petrol95);
                fuelTypes.add(superDiesel);
                fuelTypes.add(diesel);

                FuelStatusAdapter adapter = new FuelStatusAdapter(getActivity(), R.layout.adapter_view_layout_fuelstatus, fuelTypes);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<FuelModel>> call, Throwable t) {
                System.out.println("Fuel data retreived Failed");
            }
        });





//Update Fuel Availability when ListView is clicked
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), UpdateFuelStatus.class);
//                intent.putExtra("fuelType", fuelTypes.get(position).getFuelName());
//                intent.putExtra("fuelAvailability", fuelTypes.get(position).getFuelAvailability());
//                startActivity(intent);
//            }
//        });

        return v;

    }

}