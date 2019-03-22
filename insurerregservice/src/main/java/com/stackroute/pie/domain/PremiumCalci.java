package com.stackroute.pie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PremiumCalci {

    private String policyName;
    private String userName;
    private double sumInsured;
    private String  city;
    private  int ageOfEldest;
    private int noOfAdults;
    private int noOfChildren;
    private int noOfYears;


    @Override
    public String toString() {
        return "PremiumCalci{" +
                "policyName='" + policyName + '\'' +
                ", userName='" + userName + '\'' +
                ", sumInsured=" + sumInsured +
                ", city='" + city + '\'' +
                ", ageOfEldest=" + ageOfEldest +
                ", noOfAdults=" + noOfAdults +
                ", noOfChildren=" + noOfChildren +
                ", noOfYears=" + noOfYears +
                '}';
    }
}