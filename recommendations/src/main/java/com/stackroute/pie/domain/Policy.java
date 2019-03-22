package com.stackroute.pie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity
public class Policy {
    @Relationship(type= "HAS_A_POLICY_IN" ,direction=Relationship.INCOMING)

    @Id
    private long policyId;
    @UniqueElements
    private String uniqueId;
    @NotBlank
    private String insurerName;
    @NotBlank
    private String insurerLicense;
    @NotBlank
    private String policyName;
    @NotBlank

    @NotBlank
    private int minAge;

    private int maxAge;
    private String policyType;
    private String genderAvail;
    private int waitingPeriod;
    private int policyTerm;
    private int minimumPremium;
    private int minSumInsured;
    private int maxSumInsured;
    private String policyDescription;
    private List<String> diseasesCovered=new ArrayList<>();
    private List<String> cashlessHospitals = new ArrayList<>();
    private List<String> termsAndConditions = new ArrayList<>();
    private List<String> insuredList = new ArrayList<>();
    private List<String> paymentList = new ArrayList<>();
    private String imageUrl;
}

