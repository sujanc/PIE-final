package com.stackroute.pie.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="insurancelist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Policy {

    private long policyId;
    private String insurerName;
    private String policyName;
    private long minSumInsured;
    private long maxSumInsured;
    private int noOfCashLessHospitals;
    private int minAge;
    private int maxAge;
    private int waitingPeriod;
    private int policyTerm;
    private String genderAvail;
    private String policyDescription;
    private String policyType;

}
