package com.example.firestoreapp.Operations;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.firestoreapp.Authentication.HomeFragment;
import com.example.firestoreapp.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class NewReservationFragment extends Fragment {

    private Button buttonReserve;
    private TextView tvDate;
    private EditText etDate, etDropDate;
    DatePickerDialog.OnDateSetListener setListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_reservation, container, false);

        buttonReserve = view.findViewById(R.id.new_reserve_btn);
        etDate = view.findViewById(R.id.et_date);
        etDropDate = view.findViewById(R.id.et_Drop_date);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

//        tvDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(
//                        getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,
//                        setListener,year,month,day
//                );
//                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                datePickerDialog.show();
//            }
//        });
//        setListener =  new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
//
//                month++;
//                String date = day + " / " + month + " / " + year;
//                tvDate.setText(date);
//            }
//        };

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day + "/" + month + "/" + year;
                        etDate.setText(date);

                    }
                },year,month,year);
                datePickerDialog.show();
            }
        });

        etDropDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day + "/" + month + "/" + year;
                        etDropDate.setText(date);

                    }
                },year,month,year);
                datePickerDialog.show();
            }
        });

        buttonReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Reserve confirmation will be sent to you email, check your email", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
