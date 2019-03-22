package com.stackroute.pie.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolicyForm {

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
    private String diseasesCovered ;
    private List<String> cashlessHospitals = new ArrayList<>();
    private List<String> termsAndConditions = new ArrayList<>();
    private List<String> insuredList = new ArrayList<>();
    private List<String> paymentList = new ArrayList<>();
    private String imageUrl;
}