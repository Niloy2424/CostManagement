package com.example.administrator.costmanagement.Model;

/**
 * Created by Niloy Chowdhury on 2017-09-13.
 */

public class Input_Cost {

    private int id;
    private double transportAmount;
    private double foodAmount;
    private double mobileAmount;
    private double entertainmentAmount;
    private String Datetime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTransportAmount() {
        return transportAmount;
    }

    public void setTransportAmount(double transportAmount) {
        this.transportAmount = transportAmount;
    }

    public double getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(double foodAmount) {
        this.foodAmount = foodAmount;
    }

    public double getMobileAmount() {
        return mobileAmount;
    }

    public void setMobileAmount(double mobileAmount) {
        this.mobileAmount = mobileAmount;
    }

    public double getEntertainmentAmount() {
        return entertainmentAmount;
    }

    public void setEntertainmentAmount(double entertainmentAmount) {
        this.entertainmentAmount = entertainmentAmount;
    }

    public String getDatetime() {
        return Datetime;
    }

    public void setDatetime(String datetime) {
        Datetime = datetime;
    }

    public Input_Cost(double transportAmount, double foodAmount, double mobileAmount, double entertainmentAmount, String datetime) {
        this.transportAmount = transportAmount;
        this.foodAmount = foodAmount;
        this.mobileAmount = mobileAmount;
        this.entertainmentAmount = entertainmentAmount;
        Datetime = datetime;
    }

    public Input_Cost(int id, double transportAmount, double foodAmount, double mobileAmount, double entertainmentAmount, String datetime) {
        this.id = id;
        this.transportAmount = transportAmount;
        this.foodAmount = foodAmount;
        this.mobileAmount = mobileAmount;
        this.entertainmentAmount = entertainmentAmount;
        Datetime = datetime;
    }
}
