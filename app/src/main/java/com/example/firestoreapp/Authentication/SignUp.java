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
import android.widget.Toast;

import com.example.firestoreapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputLayout editTextFullNames, editTextEmail, editTextPassword, editTextConfirmPassword, editTextPhone;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_sign);

        mAuth = FirebaseAuth.getInstance();

        editTextFullNames = findViewById(R.id.fullnames);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextConfirmPassword = findViewById(R.id.comfirm_password);
        editTextPhone = findViewById(R.id.phone);
        progressBar = findViewById(R.id.progressBar);
        Button registerUser = findViewById(R.id.registerUser);
        Button signInUser = findViewById(R.id.signButton);

        signInUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!fullNamesValidation() || !emailValidation() || !passwordValidation() || !confirmPasswordValidation() || !phoneValidation()) {
                    return;
                }
                String fullNames = Objects.requireNonNull(editTextFullNames.getEditText()).getText().toString().trim();
                String email = Objects.requireNonNull(editTextEmail.getEditText()).getText().toString().trim();
                String password = Objects.requireNonNull(editTextPassword.getEditText()).getText().toString().trim();
                String confirmPassword = Objects.requireNonNull(editTextConfirmPassword.getEditText()).getText().toString().trim();
                String phone = Objects.requireNonNull(editTextPhone.getEditText()).getText().toString().trim();

                progressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            User user = new User(fullNames, email, password, phone);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {

                                                Toast.makeText(SignUp.this, "registered successfully", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                                Intent intent = new Intent(SignUp.this, Login.class);
                                                startActivity(intent);
                                                finish();
                                            } else {

                                                Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }

                                        }
                                    });

                        } else {
                            Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }

    public boolean fullNamesValidation() {

        String fullNames = Objects.requireNonNull(editTextFullNames.getEditText()).getText().toString().trim();

        if (fullNames.isEmpty()) {

            editTextFullNames.setError("Full Names Required*");
            return false;
        } else {
            editTextFullNames.setError(null);
            return true;
        }
    }

    public boolean emailValidation() {

        String email = Objects.requireNonNull(editTextEmail.getEditText()).getText().toString().trim();

        if (email.isEmpty()) {

            editTextEmail.setError("Email Required*");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            editTextEmail.setError("Please provide valid email*");
            return false;
        } else {
            editTextEmail.setError(null);
            return true;
        }
    }

    public boolean passwordValidation() {

        String password = Objects.requireNonNull(editTextPassword.getEditText()).getText().toString().trim();

        if (password.isEmpty()) {

            editTextPassword.setError("Password Required*");
            return false;
        } else if (password.length() < 6) {

            editTextPassword.setError("Password should be 6 characters or more*");
            return false;
        } else {
            editTextPassword.setError(null);
            return true;
        }
    }

    public boolean confirmPasswordValidation() {

        String password = Objects.requireNonNull(editTextPassword.getEditText()).getText().toString().trim();
        String confirmPassword = Objects.requireNonNull(editTextConfirmPassword.getEditText()).getText().toString().trim();

        if (confirmPassword.isEmpty()) {

            editTextConfirmPassword.setError("confirm Password Required*");
            return false;
        } else if (confirmPassword.length() < 6) {

            editTextConfirmPassword.setError("Password should be 6 characters or more*");
            return false;
        } else if (!confirmPassword.equals(password)) {

            editTextConfirmPassword.setError("Password do not Match*");
            Toast.makeText(this, "Password do not Match", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            editTextConfirmPassword.setError(null);
            return true;
        }
    }

    public boolean phoneValidation() {

        String phone = Objects.requireNonNull(editTextPhone.getEditText()).getText().toString().trim();

        if (phone.isEmpty()) {

            editTextPhone.setError("Phone number Required*");
            return false;
        } else if (phone.length() != 9) {

            editTextPhone.setError("Enter 9 numbers after (+27)*");
            return false;
        } else {
            editTextPhone.setError(null);
            return true;
        }
    }
}