package com.example.firestoreapp.Operations;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.firestoreapp.Authentication.HomeFragment;
import com.example.firestoreapp.DialogDisplay;
import com.example.firestoreapp.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class CostFragment extends Fragment {

    Dialog myDialog;
    private Button costCalculate;
    private ImageView fnb,absa,standardBank, capitec, instantLoan;
    private TextInputLayout rental_amount, deposit_amount,interest_rate, period, error_rate;
    private TextView estimated_amount, estimated_rate, estimated_total;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_cost, container, false);

        fnb = view.findViewById(R.id.fnb_link);
        absa = view.findViewById(R.id.absa_link);
        standardBank = view.findViewById(R.id.standard_bank_link);
        capitec = view.findViewById(R.id.capitec_link);
        costCalculate = view.findViewById(R.id.costCalc);
        instantLoan = view.findViewById(R.id.instant_link);
        rental_amount = view.findViewById(R.id.rent_amount);
        deposit_amount = view.findViewById(R.id.deposit_amount);
        interest_rate = view.findViewById(R.id.rate);
        period = view.findViewById(R.id.pay_period);
        error_rate = view.findViewById(R.id.residual_rate);
        estimated_amount = view.findViewById(R.id.estimatedAmount);
        estimated_rate = view.findViewById(R.id.estimatedRate);
        estimated_total = view.findViewById(R.id.estimatedTotal);

        costCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String rentalAmount = Objects.requireNonNull(rental_amount.getEditText()).getText().toString().trim();
                String total = Objects.requireNonNull(deposit_amount.getEditText()).getText().toString().trim();
                String interestRate = Objects.requireNonNull(interest_rate.getEditText()).getText().toString().trim();

                estimated_amount.setText(rentalAmount);
                estimated_rate.setText(interestRate);
                estimated_total.setText(total);

            }
        });

        fnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink("https://www.fnb.co.za/loans/personal-loan.html");
            }
        });
        instantLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink("https://kevinkhosaa.netlify.app/");
            }
        });
        absa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink("https://www.absa.co.za/personal/loans/for-myself/personal-loan/");
            }
        });
        standardBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink("https://www.standardbank.co.za/southafrica/personal/home");
            }
        });
        capitec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink("https://www.capitecbank.co.za/global-one/credit/term-loan/");
            }
        });

        return view;
    }

    private void goLink(String s) {

        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
