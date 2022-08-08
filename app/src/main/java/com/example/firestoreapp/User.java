package com.example.firestoreapp;

public class User {

    public String fullName, email, password, phone;

    public User(){

    }
    public User(String fullName, String email, String password, String phone){
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}
