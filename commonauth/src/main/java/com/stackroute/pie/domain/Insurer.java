package com.stackroute.pie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insurer {
    private Long insurerId;
    private String insurerName;
    private String insurerLicense; //username
    private String insurerEmail;
    private String password;
    private String insurerAddress;
    private String securityQuestion;
    private String securityAnswer;
    private List<Policy> policies;
    private Set<Role> roles = new HashSet<>();


}
