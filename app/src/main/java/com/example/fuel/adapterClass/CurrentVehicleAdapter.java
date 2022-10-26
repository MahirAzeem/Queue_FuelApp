package com.example.fuel.adapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fuel.R;
import com.example.fuel.modelClass.CurrentVehicleModel;
import com.example.fuel.modelClass.FuelStatusModel;

import java.util.List;

//ArrayAdapter Class to display List View of Current Vehicle
public class CurrentVehicleAdapter extends ArrayAdapter<CurrentVehicleModel> {

    private Context mContext;
    private int mResource;

//    Constructors
    public CurrentVehicleAdapter(@NonNull Context context, int resource, @NonNull List<CurrentVehicleModel> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    //To get the view and attach to listview
    @NonNull
    @Override
    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the Fuel Status information
        String vehicleType = getItem(position).getVehicleType();
        int vehicleCount = getItem(position).getVehicleCount();


        CurrentVehicleModel currentVehicleModel = new CurrentVehicleModel(vehicleType,vehicleCount);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvVehicleType = (TextView) convertView.findViewById(R.id.vehicleType);
        TextView tvVehicleCount = (TextView) convertView.findViewById(R.id.vehicleCount);

        tvVehicleType.setText(vehicleType);
        tvVehicleCount.setText(Integer.toString(vehicleCount));

        return convertView;

    }
}
