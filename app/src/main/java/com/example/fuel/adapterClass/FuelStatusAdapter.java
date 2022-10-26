package com.example.fuel.adapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fuel.R;
import com.example.fuel.modelClass.FuelStatusModel;

import java.util.ArrayList;
import java.util.List;

//ArrayAdapter Class to display List View of Fuel Status
public class FuelStatusAdapter extends ArrayAdapter<FuelStatusModel> {

    private Context mContext;
    private int mResource;

    //    Constructors
    public FuelStatusAdapter(@NonNull Context context, int resource, @NonNull ArrayList<FuelStatusModel> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    //To get the view and attach to listview
    @NonNull
    @Override
    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the Fuel Status information
        String fuelType = getItem(position).getFuelName();
        String fuelAvailability = getItem(position).getFuelAvailability();


        FuelStatusModel fuelStatusModel = new FuelStatusModel(fuelType,fuelAvailability);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvFuelType = (TextView) convertView.findViewById(R.id.fuelType);
        TextView tvFuelAvailability = (TextView) convertView.findViewById(R.id.fuelAvailability);

        tvFuelType.setText(fuelType);
        tvFuelAvailability.setText(fuelAvailability);

        return convertView;

    }
}
