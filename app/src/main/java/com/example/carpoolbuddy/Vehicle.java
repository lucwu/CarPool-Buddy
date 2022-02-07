package com.example.carpoolbuddy;

import java.io.Serializable;
import java.util.ArrayList;

public class Vehicle implements Serializable {
    private String owner;
    private String model;
    private int capacity;
    private String vehicleID;
    private ArrayList<String> ridersUIDs;

    private boolean open;

    public Vehicle(){};

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public ArrayList<String> getRidersEmails() {
        return ridersUIDs;
    }

    public void setRidersEmails(ArrayList<String> ridersUIDs) {
        this.ridersUIDs = ridersUIDs;
    }

    public Boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    private String vehicleType;
    private Double basePrice;

    public Vehicle(String owner, String model, int capacity, String vehicleID, boolean open, String vehicleType, double basePrice) {
        this.owner = owner;
        this.model = model;
        this.capacity = capacity;
        this.vehicleID = vehicleID;
        this.open = open;
        this.vehicleType = vehicleType;
        this.basePrice = basePrice;
    }
}
