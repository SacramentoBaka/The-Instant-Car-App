package com.example.firestoreapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.firestoreapp.Operations.CostFragment;
import com.example.firestoreapp.Operations.PromotionFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class DialogDisplay extends DialogFragment {

    public static String TAG = "DialogDisplay";
    private TextInputLayout rentAmount, deposit, payPeriod, residual, interestRate;
    public TextView totalInstalment, totalInterest, totalNoDeposit;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_pop_up, null);

        builder.setView(view).setTitle("Estimator").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return builder.create();
    }

}
