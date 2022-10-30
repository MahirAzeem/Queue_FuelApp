package com.example.fuel.adapterClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fuel.R;
import com.example.fuel.modelClass.StationModel;

import com.example.fuel.stationOwner.recyclerviewItemClick.RecyclerViewInterface;
import com.example.fuel.user.FuelStation;

import java.util.List;

public class FuelStopRecyclerViewAdapter extends RecyclerView.Adapter<FuelStopRecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    List<StationModel> fuelStops;

    public FuelStopRecyclerViewAdapter(RecyclerViewInterface recyclerViewInterface, Context context, List<StationModel> fuelStops) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.fuelStops = fuelStops;
    }

    public void setFilteredList(List<StationModel> filteredList){
        this.fuelStops =filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_card_layout, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,  int position) {


        //get provider
        String fuelProvider = fuelStops.get(position).getBrand();

        //assign values
        holder.nameOfFuelstation.setText(fuelStops.get(position).getStationName());
        holder.locationOfFuelStation.setText(fuelStops.get(position).getLocation());
        holder.brand.setText(fuelProvider);


//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("Clicked");
//                Intent intent = new Intent(context, FuelStation.class);
//                intent.putExtra("fuelStation_name",fuelStops.get(position).getStationName());
//
//                context.startActivity(intent);
//
//            }
//        });

        if (fuelProvider.equalsIgnoreCase("ceypetco")) {
            holder.fuelstationlogo.setImageResource(R.drawable.ceypetco);
        } else if (fuelProvider.equalsIgnoreCase("ioc")) {
            holder.fuelstationlogo.setImageResource(R.drawable.ioc);
        }


    }

    @Override
    public int getItemCount() {
        if (fuelStops.size() > 0) {
            return fuelStops.size();
        }
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView fuelstationlogo;
        TextView nameOfFuelstation;
        TextView locationOfFuelStation;
        TextView brand;
        CardView cardView;


        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            fuelstationlogo = itemView.findViewById(R.id.fuelStationImage);
            nameOfFuelstation = itemView.findViewById(R.id.fuelStationName);
            locationOfFuelStation = itemView.findViewById(R.id.fuelStationLocation);
            brand = itemView.findViewById(R.id.fuelStationType);
            cardView=itemView.findViewById(R.id.cardofStation);
             fuelstationlogo=itemView.findViewById(R.id.fuelStationImage);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int postion = getAdapterPosition();

                        if (postion != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(postion);
                        }
                    }
                }
            });

        }
    }
}
