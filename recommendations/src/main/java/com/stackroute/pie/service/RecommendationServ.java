package com.stackroute.pie.service;

import com.stackroute.pie.domain.*;


import java.util.List;

public interface RecommendationServ {
     Insurer createInsurer(Insurer insurer);
     Policy createPolicy(Policy policy);
     Insured createInsured(Insured insured);
     Insurer deleteInsurer(Insurer insurer);
     Policy deletePolicy(Policy policy);
     Insured deleteInsured(Insured insured);
     Insurer updateInsurer(Insurer insurer);
     Policy updatePolicy(Policy policy);
     Insured updateInsured(Insured insured);
     FamilyMembers createMembers(FamilyMembers familyMembers);
     String insurerPolicy(String insurerName,Long policyId);
     String insuredPolicy(Long policyId,String username);
     String viewPolicy(Long policyId,String username);
     String linkDependants(String memberName,String username);
    List<Policy> displayPolicy();
    List<Policy> getByUserName(String username);
   List<Policy> getByAge(String username);
    List<Policy> getByGender(String username);
    List<Policy> getByDisease(String username);
  List<Policy> getByAgeGender(String username);
    List<Policy> getByAgeDisease(int age,List<String>policyDisease);
    List<Policy> getByGenderDisease(String userGender,List<String>policyDisease);


     Insured findUser(String username);
     List<Policy> getByAgeGenderDisease(String username);
     List<Policy> policyForDependants(String username);
}
