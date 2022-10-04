package com.example.firestoreapp.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firestoreapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login extends AppCompatActivity {

    private Button signButton;
    private TextInputLayout userEmail, userPassword;
    private Button userLogInButton;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private CheckBox remember;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        remember = findViewById(R.id.rememberMe);
        userEmail = findViewById(R.id.loginEmail);
        userPassword = findViewById(R.id.loginPassword);
        progressBar = findViewById(R.id.loginProgressBar);
        signButton = findViewById(R.id.signUpButton);
        userLogInButton = findViewById(R.id.loginUserButton);
        forgotPassword = findViewById(R.id.forgotPassText);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, ForgotPassword.class));
                finish();
            }
        });

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");

        if(mAuth.getCurrentUser() != null && checkbox.equals("true")){

            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }

        userLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!emailValidation() || !passwordValidation()) {
                    return;
                }
                String email = Objects.requireNonNull(userEmail.getEditText()).getText().toString().trim();
                String password = Objects.requireNonNull(userPassword.getEditText()).getText().toString().trim();
                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if( task.isSuccessful() && mAuth.getCurrentUser().isEmailVerified()){

                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(Login.this, "logged in successful!", Toast.LENGTH_SHORT).show();


                        } else if(!task.isSuccessful()){

                            Toast.makeText(Login.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        } else{
                            Objects.requireNonNull(mAuth.getCurrentUser()).sendEmailVerification();
                            Toast.makeText(Login.this, "verify your email!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });

            }
        });

        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){

                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                    Toast.makeText(Login.this, "checked", Toast.LENGTH_SHORT).show();

                }else if(!compoundButton.isChecked()) {

                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                    Toast.makeText(Login.this, "unchecked", Toast.LENGTH_SHORT).show();

                }
            }
        });
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public boolean emailValidation() {

        String email = Objects.requireNonNull(userEmail.getEditText()).getText().toString().trim();

        if (email.isEmpty()) {

            userEmail.setError("Email Required*");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            userEmail.setError("Please provide valid email*");
            return false;
        } else {
            userEmail.setError(null);
            return true;
        }
    }

    public boolean passwordValidation() {

        String password = Objects.requireNonNull(userPassword.getEditText()).getText().toString().trim();

        if (password.isEmpty()) {

            userPassword.setError("Password Required*");
            return false;
        } else if (password.length() < 6) {

            userPassword.setError("Password should be 6 characters or more*");
            return false;
        } else {
            userPassword.setError(null);
            return true;
        }
    }
}