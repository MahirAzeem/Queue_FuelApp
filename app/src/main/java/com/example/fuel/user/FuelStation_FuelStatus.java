package com.example.fuel.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fuel.R;
import com.example.fuel.adapterClass.FuelStatusAdapter;
import com.example.fuel.modelClass.FuelStatusModel;
import com.example.fuel.stationOwner.UpdateFuelStatus;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FuelStation_FuelStatus#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FuelStation_FuelStatus extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FuelStation_FuelStatus() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FuelStation_FuelStatus.
     */
    // TODO: Rename and change types and number of parameters
    public static FuelStation_FuelStatus newInstance(String param1, String param2) {
        FuelStation_FuelStatus fragment = new FuelStation_FuelStatus();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_two, null);

        ListView mListView = (ListView) v.findViewById(R.id.listView);

        //Create the Fuel Types and their availability
        FuelStatusModel petrol92 = new FuelStatusModel("Petrol 92", "Yes");
        FuelStatusModel petrol95 = new FuelStatusModel("Petrol 95", "No");
        FuelStatusModel superDiesel = new FuelStatusModel("Super Diesel", "Yes");
        FuelStatusModel diesel = new FuelStatusModel("Diesel", "No");

        //Add Fuel types to an ArrayList
        ArrayList<FuelStatusModel> fuelTypes = new ArrayList<>();
        fuelTypes.add(petrol92);
        fuelTypes.add(petrol95);
        fuelTypes.add(superDiesel);
        fuelTypes.add(diesel);

        FuelStatusAdapter adapter = new FuelStatusAdapter(getActivity(), R.layout.adapter_view_layout_fuelstatus, fuelTypes);
        mListView.setAdapter(adapter);



        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), UpdateFuelStatus.class);
                intent.putExtra("fuelType", fuelTypes.get(position).getFuelName());
                intent.putExtra("fuelAvailability", fuelTypes.get(position).getFuelAvailability());
                startActivity(intent);
            }



        });

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_two, container, false);

        return v;

    }

}