package com.example.fuel.adapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fuel.R;
import com.example.fuel.modelClass.FuelStationModel;

import java.util.ArrayList;

//RecyclerView Adapter to display Fuel Station Recycle View
public class FuelStationAdapter extends RecyclerView.Adapter<FuelStationAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<FuelStationModel> fuelStationModelArrayList;
    private static RecyclerViewClickListener listener;

    // Constructor
    public FuelStationAdapter(Context context, ArrayList<FuelStationModel> fuelStationModelArrayList, RecyclerViewClickListener listener) {
        this.context = context;
        this.fuelStationModelArrayList = fuelStationModelArrayList;
        this.listener = listener;
    }

    //    Filter ArrayList for Search View
    public void setFilteredList(ArrayList<FuelStationModel> filteredList){
        this.fuelStationModelArrayList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FuelStationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for every recycler view item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_card_layout, parent, false);
        return new ViewHolder(view);
    }

    // For setting the data to textview and imageview of each card layout
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FuelStationModel model = fuelStationModelArrayList.get(position);

        holder.fuelStationName.setText(model.getFuelStation_name());
        holder.fuelStationLocation.setText("" + model.getFuelStation_location());
        holder.fuelStationType.setText("" + model.getFuelStation_type());
        holder.fuelStationImage.setImageResource(model.getFuelStation_image());
    }

    // Display number of card items in recycler view
    @Override
    public int getItemCount() {
        return fuelStationModelArrayList.size();
    }

    // View holder class for initializing views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final ImageView fuelStationImage;
        private final TextView fuelStationName;
        private final TextView fuelStationLocation;
        private final TextView fuelStationType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fuelStationImage = itemView.findViewById(R.id.fuelStationImage);
            fuelStationName = itemView.findViewById(R.id.fuelStationName);
            fuelStationLocation = itemView.findViewById(R.id.fuelStationLocation);
            fuelStationType = itemView.findViewById(R.id.fuelStationType);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    //    Applying OnClick Listener to each recyclerview component
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}

