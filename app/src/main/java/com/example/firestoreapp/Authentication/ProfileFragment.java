package com.example.firestoreapp.Authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.firestoreapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    public static String TAG = "ProfileFragment";


    private Button changePassword;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;
    TextView headProfName, profileUserName, profileUserEmail, profileUserPhone;
    FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        headProfName = view.findViewById(R.id.profile_username);
        profileUserName = view.findViewById(R.id.prof_names);
        profileUserEmail = view.findViewById(R.id.prof_email);
        profileUserPhone = view.findViewById(R.id.prof_phone);
        changePassword = view.findViewById(R.id.changePass);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

        auth = FirebaseAuth.getInstance();

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){

                    String user_name = userProfile.fullName;
                    String user_email = userProfile.email;
                    String user_phone = userProfile.phone;

                    headProfName.setText(user_name.toUpperCase());
                    profileUserName.setText(user_name);
                    profileUserEmail.setText(user_email);
                    profileUserPhone.setText(user_phone);

                    changePassword.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            auth.sendPasswordResetEmail(user_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {

                                        Toast.makeText(getActivity(), "Link sent, please check you email", Toast.LENGTH_SHORT).show();
                                    } else {

                                        Toast.makeText(getActivity(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}
