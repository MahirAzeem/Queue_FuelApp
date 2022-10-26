package com.example.fuel.stationOwnerLogin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.fuel.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StationOwner_SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StationOwner_SignUpFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StationOwner_SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StationOwner_SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StationOwner_SignUpFragment newInstance(String param1, String param2) {
        StationOwner_SignUpFragment fragment = new StationOwner_SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


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

        String[] LocationList = {"Dehiwala", "Kalubowila", "Ratmalana"};
        String[] TypeList = {"IOC", "Ceypetco"};
        AutoCompleteTextView stationLocation;
        AutoCompleteTextView stationType;
        ArrayAdapter<String> adapterStationLocations;
        ArrayAdapter<String> adapterStationTypes;

        View v =inflater.inflate(R.layout.fragment_sign_up2, null);

        stationLocation = v.findViewById(R.id.stationLocations);
        stationType = v.findViewById(R.id.stationType);

        adapterStationLocations = new ArrayAdapter<String>(getActivity(), R.layout.list_item, LocationList);
        adapterStationTypes = new ArrayAdapter<String>(getActivity(), R.layout.list_item, TypeList);

        stationLocation.setAdapter(adapterStationLocations);
        stationType.setAdapter(adapterStationTypes);

        stationLocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
//                Toast.makeText(getActivity(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        stationType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = adapterView.getItemAtPosition(position).toString();
//                Toast.makeText(getActivity(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_sign_up2, container, false);

        return v;

    }
}