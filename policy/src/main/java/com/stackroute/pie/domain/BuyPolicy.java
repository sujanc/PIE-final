package com.stackroute.pie.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyPolicy {
    private int id;
    private String policyName;
    private long policyId;
    private String insurerName;
    private String username;
    private String email;
}
