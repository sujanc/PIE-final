package com.stackroute.pie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {


    private int requestId;
    private String requestStatus;
    private String newInsurancePolicy;
    private String newInsuranceCompany;



}

