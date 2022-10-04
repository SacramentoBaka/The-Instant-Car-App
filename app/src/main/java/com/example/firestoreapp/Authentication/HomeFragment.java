package com.example.firestoreapp.Authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.firestoreapp.Operations.CostFragment;
import com.example.firestoreapp.Operations.ManReservationFragment;
import com.example.firestoreapp.Operations.ManTermFragment;
import com.example.firestoreapp.Operations.NewReservationFragment;
import com.example.firestoreapp.Operations.PromotionFragment;
import com.example.firestoreapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    private CardView cardProfile, cardNewReserve, cardManageReserve, cardManageTerm, cardCost, cardPromotion;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;
    TextView homeNames;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        homeNames = view.findViewById(R.id.home_username);
        cardProfile = view.findViewById(R.id.card_profile);
        cardNewReserve = view.findViewById(R.id.card_new_reserve);
        cardManageReserve = view.findViewById(R.id.card_manage_reserve);
        cardManageTerm = view.findViewById(R.id.card_manage_term);
        cardCost = view.findViewById(R.id.card_cost);
        cardPromotion = view.findViewById(R.id.card_promotion);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();


        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){

                    String user_name = userProfile.fullName;
                    homeNames.setText(user_name.toUpperCase());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new ProfileFragment());
                fr.commit();
            }
        });
        cardNewReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new NewReservationFragment());
                fr.commit();
            }
        });
        cardManageReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new ManReservationFragment());
                fr.commit();
            }
        });
        cardManageTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new ManTermFragment());
                fr.commit();
            }
        });
        cardCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new CostFragment());
                fr.commit();
            }
        });
        cardPromotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new PromotionFragment());
                fr.commit();
            }
        });

        return view;
    }
}
