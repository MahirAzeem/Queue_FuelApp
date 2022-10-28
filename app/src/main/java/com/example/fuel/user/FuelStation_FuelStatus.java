package com.example.fuel.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.fuel.R;
import com.example.fuel.adapterClass.FuelStatusAdapter;
import com.example.fuel.modelClass.FuelStatusModel;

import java.util.ArrayList;

//Fuel Status Fragment to display the availability of fuel
public class FuelStation_FuelStatus extends Fragment {


//    Implementing List View for Fuel Status
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.user_fuel_status, null);

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

        FuelStatusAdapter adapter = new FuelStatusAdapter(getActivity(), R.layout.adapter_view_layout_fuelstatus, fuelTypes);
        mListView.setAdapter(adapter);


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