package com.example.fuel.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.fuel.Controller.QueueInterface;
import com.example.fuel.Controller.StationInterface;
import com.example.fuel.R;
import com.example.fuel.adapterClass.CurrentVehicleAdapter;
import com.example.fuel.modelClass.CurrentVehicleModel;
import com.example.fuel.modelClass.FuelModel;
import com.example.fuel.modelClass.FuelStationModel;
import com.example.fuel.modelClass.QueueModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//    Implementing List View for to identify the count of Vehicle Types
public class FuelStation_CurrentVehicle extends Fragment {


    private StationInterface stationInterface;
    private QueueInterface queueInterface;

    List<QueueModel> queueModelList;

    int bikeCount = 0;
    int threeWheelCount = 0;
    int heavyVehicleCount = 0;
    int lightVehicleCount = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_currrent_vehicle, null);

        ListView mListView = (ListView) v.findViewById(R.id.listView);

        String  fuelStation_name = "nulllllll";


//        FuelStationModel  FMM =  new FuelStationModel();
//        System.out.println("  FMM.getFuelStation_name();"+  FMM.getFuelStation_name());
//
//        Bundle extras = getActivity().getIntent().getExtras();
//        if (extras != null) {
//            fuelStation_name = extras.getString("fuelStation_name");
//
//        }
//
//        System.out.println("yyyyyyyyyyyyyyyyyyyyyyyy"+fuelStation_name);




//        FuelStation activity = (FuelStation) getActivity();
//        String receivedFuelStationName = activity.getMyData();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ahmedameer-001-site1.atempurl.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        stationInterface = retrofit.create(StationInterface.class);
        queueInterface = retrofit.create(QueueInterface.class);




//        String queueStationName = receivedFuelStationName;
        String queueStationName = "Ahmed";
        Call<List<QueueModel>> call = queueInterface.getQueue();

        call.enqueue(new Callback<List<QueueModel>>() {

            @Override
            public void onResponse(Call<List<QueueModel>> call, Response<List<QueueModel>> response) {
                System.out.println("Queue  Retreived Sucess ");
                queueModelList = response.body();

                for(int i =0 ; i<queueModelList.size(); i++){

                    if(queueModelList.get(i).getStationName().equals(queueStationName)){
                        if(queueModelList.get(i).getVehicleType().equals("Bike")) {
                            bikeCount = bikeCount +1;
                        }else   if(queueModelList.get(i).getVehicleType().equals("Light Vehicle")) {
                            lightVehicleCount = lightVehicleCount +1;
                        }else   if(queueModelList.get(i).getVehicleType().equals("Heavy Vehicle")) {
                            heavyVehicleCount = heavyVehicleCount +1;
                        }else   if(queueModelList.get(i).getVehicleType().equals("Three Wheel")) {
                            threeWheelCount = threeWheelCount +1;
                        }
                    }
                }


                //Create the Vehicle Types and their availability
                CurrentVehicleModel lightVehicle = new CurrentVehicleModel("Light Vehicle", lightVehicleCount);
                CurrentVehicleModel heavyVehicle = new CurrentVehicleModel("Heavy Vehicle", heavyVehicleCount);
                CurrentVehicleModel bike = new CurrentVehicleModel("Bike", bikeCount);
                CurrentVehicleModel threeWheel = new CurrentVehicleModel("Three Wheel", threeWheelCount);

                //Add Vehicle types to an ArrayList
                ArrayList<CurrentVehicleModel> vehicleTypes = new ArrayList<>();
                vehicleTypes.add(lightVehicle);
                vehicleTypes.add(heavyVehicle);
                vehicleTypes.add(bike);
                vehicleTypes.add(threeWheel);

                CurrentVehicleAdapter adapter = new CurrentVehicleAdapter(getActivity(), R.layout.adapter_view_layout_currentvehicle, vehicleTypes);
                mListView.setAdapter(adapter);





            }

            @Override
            public void onFailure(Call<List<QueueModel>> call, Throwable t) {
                System.out.println("Queue  Retreived Failed ");
            }
        });








        return v;
    }
}