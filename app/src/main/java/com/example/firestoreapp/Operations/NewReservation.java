package com.example.firestoreapp.Operations;

public class NewReservation {

    public String pickUpLocation, pickUpDate, dropLocation, dropDate;

    public NewReservation(){

    }
    public NewReservation(String fullName, String email, String password, String phone){
        this.pickUpLocation = fullName;
        this.pickUpDate = email;
        this.dropLocation = password;
        this.dropDate = (phone);
    }
}
