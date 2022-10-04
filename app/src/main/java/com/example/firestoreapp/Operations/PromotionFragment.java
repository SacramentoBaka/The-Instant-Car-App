package com.example.firestoreapp.Operations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firestoreapp.Authentication.HomeFragment;
import com.example.firestoreapp.CarListAdapter;
import com.example.firestoreapp.MyCarData;
import com.example.firestoreapp.R;

public class PromotionFragment extends Fragment {

//    String[] names = {"Polo Vivo", "Polo R-Line", "Golf R 7.5", "BMW M4", "BMW M2", "Mercedes V250 D", "Audi S3","Toyota Corolla 1.2",
//            "Range Rover Sport SVR", "Mercedes G63 Amg", "Toyota Hilux GD6", "Ford Wild-track", "Toyota Legend 50", "Toyota Supra"};

    public static String TAG = "PromotionFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotion, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        MyCarData[] myCarData = new MyCarData[]{

                new MyCarData(R.drawable.vw_golf_viii, "Golf 8 R", "2.0l , 5-door , petrol","R1500 p/w"),
                new MyCarData(R.drawable.bmw_m4, "BMW M4 Cabriolet", "3.0l , 2-door , petrol","R2500 p/w"),
                new MyCarData(R.drawable.v250d, "Mercedes V250D", "3.0D , Family Car , Diesel","R1900 p/w"),
                new MyCarData(R.drawable.vivo, "Polo Vivo", "1.0l , 5-door , petrol","R500 p/w"),
                new MyCarData(R.drawable.toyota_50, "Toyota Legend 50 GD-6", "2.8l , Double-Cap , Diesel","R1100 p/w"),
                new MyCarData(R.drawable.bmw_m2, "BMW M2", "3.0l , 2-door , petro","R1200 p/w"),
                new MyCarData(R.drawable.audis3, "AUDI S3", "2.0l , 4-door , petro","R1100 p/w"),
                new MyCarData(R.drawable.wildtrak, "Ford Ranger WildTrack", "3.2l , Extra-Cap , Diesel","R800 p/w"),
                new MyCarData(R.drawable.range_rover_svr, "Range Rover Sport SVR", "4.0l , 5-door , Petrol","R3100 p/w"),
                new MyCarData(R.drawable.rr, "Rolls-Royce Phantom", "6.7l , 5-door , Petrol","R51000 p/w"),
                new MyCarData(R.drawable.rari, " Ferrari F812", "4.0l , 2-door , Petrol","R51000 p/w"),
                new MyCarData(R.drawable.urus, "Lamborghini Urus", "4.0l , 5-door , Petrol","R21000 p/w"),
                new MyCarData(R.drawable.cullinun, "Rolls-Royce Cullinan", "6.7l , 5-door , Petrol","R53000 p/w"),
        };

        CarListAdapter myCarAdapter = new CarListAdapter(myCarData, PromotionFragment.this);
        recyclerView.setAdapter(myCarAdapter);

        return view;
    }
}
