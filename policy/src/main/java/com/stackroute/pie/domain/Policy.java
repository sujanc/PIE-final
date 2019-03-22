package com.stackroute.pie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

//Pojo object for storing the policies
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "policylist")
public class Policy {
    @Id
    @UniqueElements
    private String uniqueId;
    @NotBlank
    private String insurerName;
    @NotBlank
    private String insurerLicense;
    @NotBlank
    private String policyName;
    @NotBlank
    private long policyId;
    @NotBlank
    private int minAge;
    @NotBlank
    private int maxAge;
    private String policyType;
    private String genderAvail;
    private int waitingPeriod ;
    private int policyTerm;
    private int minSumInsured;
    private int maxSumInsured;
    private String policyDescription = "";
    private List<String> diseasesCovered = new ArrayList<>();
    private List<String> cashlessHospitals = new ArrayList<>();
    private List<String> termsAndConditions = new ArrayList<>();
    private List<String> insuredList = new ArrayList<>();
    private List<String> paymentList = new ArrayList<>();
    private String imageUrl;

   //Custom Constructor for adding new policy
    public Policy( String insurerName, String insurerLicense, String policyName, long policyId, int minAge, int maxAge,
                   int minSumInsured, int maxSumInsured, String policyDescription, String policyType,
                   String genderAvail, int waitingPeriod, int policyTerm,String imageUrl) {
        this.uniqueId = insurerName + policyName;
        this.insurerName = insurerName;
        this.policyName = policyName;
        this.policyId = policyId;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.policyType = policyType;
        this.genderAvail = genderAvail;
        this.waitingPeriod = waitingPeriod;
        this.policyTerm = policyTerm;
        this.minSumInsured = minSumInsured;
        this.maxSumInsured = maxSumInsured;
        this.insurerLicense = insurerLicense;
        this.policyDescription = policyDescription;
        this.imageUrl=imageUrl;
    }

    //Custom Constructor for adding new policy
    public Policy( String insurerName, String insurerLicense, String policyName, long policyId, int minAge, int maxAge,
                   int minSumInsured, int maxSumInsured, String policyDescription, String policyType,
                   String genderAvail, int waitingPeriod, int policyTerm,String imageUrl,List<String> diseasesCovered,
                   List<String> cashlessHospitals) {
        this.uniqueId = insurerName + policyName;
        this.insurerName = insurerName;
        this.policyName = policyName;
        this.policyId = policyId;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.policyType = policyType;
        this.genderAvail = genderAvail;
        this.waitingPeriod = waitingPeriod;
        this.policyTerm = policyTerm;
        this.minSumInsured = minSumInsured;
        this.maxSumInsured = maxSumInsured;
        this.insurerLicense = insurerLicense;
        this.policyDescription = policyDescription;
        this.imageUrl=imageUrl;
        this.diseasesCovered = diseasesCovered;
        this.cashlessHospitals = cashlessHospitals;
    }
}