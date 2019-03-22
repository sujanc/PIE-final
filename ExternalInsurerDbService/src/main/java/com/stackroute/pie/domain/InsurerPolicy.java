package com.stackroute.pie.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document(collection="policylist")
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class InsurerPolicy {

    @Id
    @UniqueElements
    private String uniqueId;
    private String insurerName;
    private String insurerLicense;
    private String policyName;
    private long policyId;
    private int minAge;
    private int maxAge;
    private String policyType ;
    private String genderAvail;
    private int waitingPeriod ;
    private int policyTerm ;
    private int minSumInsured ;
    private int maxSumInsured ;
    private String policyDescription ;
    private List<String> diseasesCovered = new ArrayList<>();
    private List<String> cashlessHospitals = new ArrayList<>();
    private List<String> termsAndConditions = new ArrayList<>();
    private List<String> insuredList = new ArrayList<>();
    private List<String> paymentList = new ArrayList<>();
    private String imageUrl;


    public InsurerPolicy( String insurerName, String insurerLicense, String policyName, long policyId, int minAge, int maxAge,
                   int minSumInsured, int maxSumInsured, String policyDescription, String policyType,
                   String genderAvail, int waitingPeriod, int policyTerm) {
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
    }
}
