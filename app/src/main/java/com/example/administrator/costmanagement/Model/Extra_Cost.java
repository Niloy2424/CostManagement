package com.example.administrator.costmanagement.Model;

/**
 * Created by Niloy Chowdhury on 2017-08-31.
 */

public class Extra_Cost {
    public int id;
    public String extraCostPurposeName;
    public String dateTime;
    public Double extraCostPurposeAmount;

    public Extra_Cost(String extraCostPurposeName, Double extraCostPurposeAmount) {
        this.extraCostPurposeName = extraCostPurposeName;
        this.dateTime = dateTime;
        this.extraCostPurposeAmount = extraCostPurposeAmount;
    }

    public Extra_Cost(int id, String extraCostPurposeName, Double extraCostPurposeAmount,String dateTime) {

        this.id = id;
        this.extraCostPurposeName = extraCostPurposeName;
        this.dateTime = dateTime;
        this.extraCostPurposeAmount = extraCostPurposeAmount;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExtraCostPurposeName() {
        return extraCostPurposeName;
    }

    public void setExtraCostPurposeName(String extraCostPurposeName) {
        this.extraCostPurposeName = extraCostPurposeName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Double getExtraCostPurposeAmount() {
        return extraCostPurposeAmount;
    }

    public void setExtraCostPurposeAmount(Double extraCostPurposeAmount) {
        this.extraCostPurposeAmount = extraCostPurposeAmount;
    }
}

