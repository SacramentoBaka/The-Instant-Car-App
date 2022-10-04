package com.example.firestoreapp.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firestoreapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ForgotPassword extends AppCompatActivity {

    private Button iosBackArrow;
    private TextInputLayout userResetEmail;
    private Button userResetButton;
    private ProgressBar resetProgressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_forgot_password);

        iosBackArrow = findViewById(R.id.back_arrow);
        userResetEmail = findViewById(R.id.resetEmail);
        userResetButton = findViewById(R.id.resetButton);
        resetProgressBar = findViewById(R.id.userResetProgressBar);

        iosBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPassword.this, Login.class));
                finish();
            }
        });

        auth = FirebaseAuth.getInstance();

        userResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!emailValidation()) {
                    return;
                }
                resetProgressBar.setVisibility(View.VISIBLE);

                String email = Objects.requireNonNull(userResetEmail.getEditText()).getText().toString().trim();
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            resetProgressBar.setVisibility(View.GONE);
                            Toast.makeText(ForgotPassword.this, "Reset email sent, please check you email", Toast.LENGTH_SHORT).show();
                        } else {

                            resetProgressBar.setVisibility(View.GONE);
                            Toast.makeText(ForgotPassword.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public boolean emailValidation() {

        String email = Objects.requireNonNull(userResetEmail.getEditText()).getText().toString().trim();

        if (email.isEmpty()) {

            userResetEmail.setError("Email Required*");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            userResetEmail.setError("Please provide valid email*");
            return false;
        } else {
            userResetEmail.setError(null);
            return true;
        }
    }
}