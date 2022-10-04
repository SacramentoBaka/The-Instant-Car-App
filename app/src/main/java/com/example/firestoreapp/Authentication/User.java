package com.example.firestoreapp.Authentication;

public class User {

    public String fullName, email, password, phone;

    public User(){

    }
    public User(String fullName, String email, String password, String phone){
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phone = ("0" +phone);
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
