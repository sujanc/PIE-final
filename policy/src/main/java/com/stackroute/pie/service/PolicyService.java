package com.stackroute.pie.service;

import com.stackroute.pie.domain.Policy;
import com.stackroute.pie.exceptions.InsuredPoliciesNotFoundException;
import com.stackroute.pie.exceptions.InsurerNotFoundException;
import com.stackroute.pie.exceptions.PolicyAlreadyExistsException;
import com.stackroute.pie.exceptions.PolicyNotFoundException;
import com.stackroute.pie.message.PolicyForm;

import java.util.List;
import java.util.Set;

public interface PolicyService {
    //Method for adding the policy for insurer
    Policy addPolicy(PolicyForm policyForm) throws PolicyAlreadyExistsException;
    //Method for deleting the policy for insurer
    Policy deletePolicy(String insurerName, String policyName) throws PolicyNotFoundException;
    //Method for getting the policy for insurer
    List<Policy> getPolicy(String insurerLicense) throws InsurerNotFoundException;
    //Method to find policy based on insurerName and policyName for more view
    Policy getPolicyForUser(String insurerName, String policyName) throws InsurerNotFoundException, PolicyNotFoundException;
    //Method for adding the inusredName into the policy mentioned
    Policy addInsured(String insurerName,String policyName, String insuredName) throws InsurerNotFoundException, PolicyNotFoundException;
    //Method for deleting the insuredName from the policy
    Policy deleteInsured(String insurerName,String policyName, String insuredName) throws InsurerNotFoundException, PolicyNotFoundException;
    //Method to get the policies of the Insured
    List<Policy> getPoliciesOfUser(String insuredName) throws InsuredPoliciesNotFoundException;
    //Method to get Insurer Names
    Set<String> getInsurerList();

    Policy getPolicyByPolicyName(String policyName) throws PolicyNotFoundException;

    List<Policy> getAllPolicies();
}
