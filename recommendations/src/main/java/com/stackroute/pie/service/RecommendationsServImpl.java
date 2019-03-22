package com.stackroute.pie.service;


import com.stackroute.pie.domain.FamilyMembers;
import com.stackroute.pie.domain.Insured;
import com.stackroute.pie.domain.Insurer;
import com.stackroute.pie.domain.Policy;
import com.stackroute.pie.repository.RecommendationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

@Service
public class RecommendationsServImpl implements RecommendationServ {

    @Autowired
    private RecommendationsRepo recommendationsRepo;

    @Override
    public Insurer createInsurer(Insurer insurer) {
        return recommendationsRepo.newInsurer(insurer.getInsurerId(), insurer.getInsurerName(), insurer.getInsurerLicense());
    }

    @Override
    public Policy createPolicy(Policy policy) {
        return recommendationsRepo.newPolicy(policy.getUniqueId(),policy.getPolicyId(),policy.getInsurerName(),policy.getInsurerLicense(),policy.getPolicyName(),policy.getMinAge(),policy.getMaxAge(),policy.getGenderAvail(),policy.getDiseasesCovered(),policy.getPolicyType(),policy.getPolicyDescription(),policy.getImageUrl());
    }

    @Override
    public Insured createInsured(Insured insured) {
        return recommendationsRepo.newInsured(insured.getInsuredId(),insured.getUsername(),insured.getGender(),insured.getAge(),insured.getExistingDisease(),insured.getNumberOfDependants());
    }

    @Override
    public Insurer deleteInsurer(Insurer insurer) {
        return recommendationsRepo.deleteInsurer(insurer.getInsurerLicense());
    }

    @Override
    public Policy deletePolicy(Policy policy) {
        return recommendationsRepo.deletePolicy(policy.getPolicyId());
    }

    @Override
    public Insured deleteInsured(Insured insured) {
        return recommendationsRepo.deleteInsured(insured.getInsuredId());
    }

    @Override
    public Insurer updateInsurer(Insurer insurer) {
        return recommendationsRepo.updateInsurer(insurer.getInsurerId(),insurer.getInsurerName(),insurer.getInsurerLicense());
    }


    @Override
    public Policy updatePolicy(Policy policy) {
        return recommendationsRepo.updatePolicy(policy.getPolicyId(),policy.getInsurerName(),policy.getPolicyName(),policy.getMinAge(),policy.getMaxAge(),policy.getGenderAvail(),policy.getDiseasesCovered(),policy.getPolicyType());

    }

    @Override
    public Insured updateInsured(Insured insured) {
        return recommendationsRepo.updateInsured(insured.getInsuredId(),insured.getUsername(),insured.getGender(),insured.getAge(),insured.getExistingDisease(),insured.getNumberOfDependants());
    }

    @Override
    public FamilyMembers createMembers(FamilyMembers familyMembers) {
        return recommendationsRepo.newFamilyMembers(familyMembers.getUsername(),familyMembers.getMemberName(),familyMembers.getMemberAge(),familyMembers.getRelation(),familyMembers.getMemberGender());
    }

    @Override
    public String insurerPolicy(String insurerName, Long policyId) {
        recommendationsRepo.insurerPolicy(insurerName, policyId);
        return " ";
    }

    @Override
    public String insuredPolicy(Long policyId, String username) {
        recommendationsRepo.insuredPolicy(policyId,username);
        return " ";
    }

    @Override
    public String viewPolicy(Long policyId, String username) {
        recommendationsRepo.viewedPolicy(policyId,username);
        return " ";
    }

    @Override
    public String linkDependants(String memberName, String username) {
        recommendationsRepo.addDependant(memberName,username);
        return " ";
    }

    @Override
    public List<Policy> getByAgeGender(String username) {
        Insured user=recommendationsRepo.findUser(username);
        Long age =user.getAge();

        String gender=user.getGender();

            return recommendationsRepo.findByAgeGender(age, gender);

    }

    @Override
    public List<Policy> getByAgeDisease(int age, List<String> policyDisease) {
        return recommendationsRepo.findByAgeDisease(age,policyDisease);
    }

    @Override
    public List<Policy> getByGenderDisease(String userGender, List<String> policyDisease) {
        return recommendationsRepo.findByGenderDisease(userGender,policyDisease);
    }

    @Override
    public Insured findUser(String username) {
        return recommendationsRepo.findUser(username);
    }

    @Override
    public List<Policy> getByAgeGenderDisease(String username) {
        Insured user=recommendationsRepo.findUser(username);
        Long age=user.getAge();
        String gender=user.getGender();
        String existingDisease=user.getExistingDisease();
        return recommendationsRepo.findByAgeGenderDisease(age,gender,existingDisease);

            }

    @Override
    public List<Policy> policyForDependants(String username) {
        FamilyMembers familyMembers =recommendationsRepo.findDependants(username);
        String memberGender = familyMembers.getMemberGender();
        Long memberAge=familyMembers.getMemberAge();
        return recommendationsRepo.findByAgeGender(memberAge,memberGender);

    }

    @Override
    public List<Policy> displayPolicy() {
        return recommendationsRepo.findViewedPolicy();
    }

    @Override
    public List<Policy> getByUserName(String username) {
        return recommendationsRepo.findByuserName(username);
    }

    @Override
    public List<Policy> getByAge(String username) {
        Insured user=recommendationsRepo.findUser(username);
        Long age =user.getAge();
        return recommendationsRepo.findByAge(age);

    }


    @Override
    public List<Policy> getByGender(String username) {
        Insured user=recommendationsRepo.findUser(username);
        String gender=user.getGender();
         return recommendationsRepo.findByGender(gender);
    }

    @Override
    public List<Policy> getByDisease(String username) {

        Insured user = recommendationsRepo.findUser(username);
        String existingDisease = user.getExistingDisease();
        List<String> policyDiseases = new ArrayList<>();
        policyDiseases.add(existingDisease);
        return recommendationsRepo.findByDisease(policyDiseases);
    }
}