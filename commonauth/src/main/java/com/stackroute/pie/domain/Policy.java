package com.stackroute.pie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policy {

    private String existingInsuranceCompany;
    private int existingPolicyId;
    private String existingPolicyName;
    private String[] diseases;


    public String getExistingInsuranceCompany() {
        return existingInsuranceCompany;
    }

    public void setExistingInsuranceCompany(String existingInsuranceCompany) {
        this.existingInsuranceCompany = existingInsuranceCompany;
    }


}
