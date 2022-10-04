package com.example.firestoreapp.Operations;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.firestoreapp.Authentication.HomeFragment;
import com.example.firestoreapp.R;

import java.util.Calendar;

public class ManTermFragment extends Fragment {

    private EditText currentDate, newDropDate;
    DatePickerDialog.OnDateSetListener setListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_term_reservation, container, false);

        currentDate = view.findViewById(R.id.et_currentDrop_date);
        newDropDate = view.findViewById(R.id.et_newDrop_date);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        currentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day + "/" + month + "/" + year;
                        currentDate.setText(date);

                    }
                },year,month,year);
                datePickerDialog.show();
            }
        });
        newDropDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day + "/" + month + "/" + year;
                        newDropDate.setText(date);

                    }
                },year,month,year);
                datePickerDialog.show();
            }
        });

        return view;
    }
}
