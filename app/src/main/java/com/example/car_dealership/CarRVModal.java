package com.example.car_dealership;

public class CarRVModal {
    private String carName;
    private String carPrice;
    private String carDescription;
    private String carModel;
    private String carImage;
    private String carLink;
    private String carID;

    public CarRVModal() {
    }

    public CarRVModal(String carName, String carPrice, String carDescription, String carModel, String carImage, String carLink, String carID) {
        this.carName = carName;
        this.carPrice = carPrice;
        this.carDescription = carDescription;
        this.carModel = carModel;
        this.carImage = carImage;
        this.carLink = carLink;
        this.carID = carID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public String getCarLink() {
        return carLink;
    }

    public void setCarLink(String carLink) {
        this.carLink = carLink;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }
}
