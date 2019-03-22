package com.stackroute.pie.services;

import com.stackroute.pie.domain.*;
import com.stackroute.pie.message.request.SignUpForm;
import com.stackroute.pie.repository.InsurerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InsurerServiceImpl implements  InsurerService{

    private InsurerRepository insurerRepository;

    @Autowired
    public InsurerServiceImpl(InsurerRepository insurerRepository) {
        this.insurerRepository = insurerRepository;
    }


    //Method to add insurer to the Database
    @Override
    public Insurer addInsurer(SignUpForm signUpRequest) {
        List<Insurer> insurerList = insurerRepository.findAll();
        Insurer insurer = new Insurer(signUpRequest.getInsurerName(),signUpRequest.getInsurerLicense(),
                signUpRequest.getInsurerEmail(),signUpRequest.getPassword(),signUpRequest.getInsurerAddress(),
                signUpRequest.getSecurityQuestion(),signUpRequest.getSecurityAnswer());
        if(insurerList.isEmpty()) {
            insurer.setInsurerId((long) 1);
        }
        else {
            insurer.setInsurerId((long) (insurerList.size()+1));
        }
        Set<Role> roles = new HashSet<>();
        Role userrRole = new Role();
        userrRole.setName(RoleName.ROLE_INSURER);
        roles.add(userrRole);
        insurer.setRoles(roles);
        return insurerRepository.save(insurer);
    }

    //Method to calculate the premium based on the POJO
    @Override
    public long calculatePremium(PremiumCalci premiumCalci){

        long premium = 1000;
        if (premiumCalci.getAgeOfEldest() < 40) {
            premium *= 1.1;

        }
        else{
            // Add 20% per 5 years above 40
            int age = premiumCalci.getAgeOfEldest() - 40;
            while (age >= 5) {
                premium *= 1.2;
                age -= 5;
            }

        }
        List<String> policyList = new ArrayList<>();
        List<Float> policyValue = new ArrayList<>();
        policyList.add("Apollo-cancer");
        policyList.add("Apollo-children");
        policyList.add("Apollo-Family");


        policyList.add("MaxBupa-cancer");
        policyList.add("MaxBupa-children");
        policyList.add("MaxBupa-Family");


        policyList.add("StarHealth-cancer");
        policyList.add("StarHealth-children");
        policyList.add("StarHealth-Family");

        policyValue.add((float) 1.3);
        policyValue.add((float) 1.2);
        policyValue.add((float) 1.1);

        policyValue.add((float) 1.2);
        policyValue.add((float) 1.3);
        policyValue.add((float) 1.5);

        policyValue.add((float) 1.5);
        policyValue.add((float) 1.075);
        policyValue.add((float) 1.15);

        String policyName = premiumCalci.getPolicyName();

        int index = policyList.indexOf(policyName);
        if(index >= 0)
            premium *= policyValue.get(index);
        else {
            premium *= (float) 1.01;
        }

        premium *= 1 + (premiumCalci.getSumInsured()/1000000);

        premium *= premiumCalci.getNoOfAdults();
        premium += (premiumCalci.getNoOfChildren()*.5);

        premium *= premiumCalci.getNoOfYears();

        return premium;

    }
}
