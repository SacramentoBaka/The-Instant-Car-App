package com.example.firestoreapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firestoreapp.Authentication.ProfileFragment;
import com.example.firestoreapp.Operations.ManTermFragment;
import com.example.firestoreapp.Operations.PromotionFragment;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.MyViewHolder> {

    String[] carList;
    MyCarData[] myCarData;
    Context context;

    public CarListAdapter(MyCarData[] myCarData, PromotionFragment promotionFragment) {
        this.myCarData = myCarData;
        this.context = promotionFragment.getContext();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.car_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final MyCarData myCarDataList = myCarData[position];

        holder.promCarImage.setImageResource(myCarDataList.getCarImage());
        holder.promCarName.setText(myCarDataList.getCarName());
        holder.promCarDescription.setText(myCarDataList.getCarDescription());
        holder.promCarPrice.setText(myCarDataList.getCarPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, myCarDataList.getCarName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return myCarData.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView promCarImage;
        TextView promCarName;
        TextView promCarDescription;
        TextView promCarPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            promCarImage = itemView.findViewById(R.id.car_imageView);
            promCarName = itemView.findViewById(R.id.car_name);
            promCarDescription = itemView.findViewById(R.id.car_description);
            promCarPrice = itemView.findViewById(R.id.car_price);

        }
    }

}
