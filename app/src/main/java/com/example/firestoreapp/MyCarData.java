package com.example.firestoreapp;

public class MyCarData {

    private Integer carImage;
    private String carName;
    private String carDescription;
    private String carPrice;

    public MyCarData(Integer carImage, String carName, String carDescription, String carPrice) {
        this.carImage = carImage;
        this.carName = carName;
        this.carDescription = carDescription;
        this.carPrice = carPrice;
    }

    public Integer getCarImage() {
        return carImage;
    }

    public void setCarImage(Integer carImage) {
        this.carImage = carImage;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }
}
