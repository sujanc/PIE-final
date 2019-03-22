package com.stackroute.pie.service;

import com.stackroute.pie.domain.Policy;
import com.stackroute.pie.exceptions.InsuredPoliciesNotFoundException;
import com.stackroute.pie.exceptions.InsurerNotFoundException;
import com.stackroute.pie.exceptions.PolicyAlreadyExistsException;
import com.stackroute.pie.exceptions.PolicyNotFoundException;
import com.stackroute.pie.message.PolicyForm;
import com.stackroute.pie.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PolicyServiceImpl implements PolicyService {

    private PolicyRepository policyRepository;
    private KafkaTemplate<String, Policy> kafkaTemplate;

    private PolicyNotFoundException policyNotFoundException = new PolicyNotFoundException("Policy not found exception");
    private PolicyAlreadyExistsException policyAlreadyExistsException = new PolicyAlreadyExistsException("Policy already exists excpetion");
    private InsuredPoliciesNotFoundException insuredPoliciesNotFoundException = new InsuredPoliciesNotFoundException("Insured policies not found exception");
    private InsurerNotFoundException insurerNotFoundException = new InsurerNotFoundException("Insurer not found exception");

    private Policy policyGlobal = new Policy();
    private List<Policy> policyListGlobal = new ArrayList<>();


    @Autowired
    PolicyServiceImpl(PolicyRepository policyRepository,KafkaTemplate<String, Policy> kafkaTemplate) {
        this.policyRepository = policyRepository;
        this.kafkaTemplate = kafkaTemplate;
    }
    //Method for adding the policy for insurer
    @Override
    public Policy addPolicy(PolicyForm policy) throws PolicyAlreadyExistsException {
        if(policyRepository.existsByPolicyIdAndInsurerName(policy.getPolicyId(),policy.getInsurerName())) {
            throw policyAlreadyExistsException;
        }
        Policy policy1 = new Policy(policy.getInsurerName(),policy.getInsurerLicense(),policy.getPolicyName(),
                policy.getPolicyId(),policy.getMinAge(),policy.getMaxAge(),policy.getMinSumInsured(),
                policy.getMaxSumInsured(), policy.getPolicyDescription(), policy.getPolicyType(),
                policy.getGenderAvail(), policy.getWaitingPeriod(),policy.getPolicyTerm(),policy.getImageUrl());
        if(policy.getCashlessHospitals().isEmpty()) {
            List<String> hospitalList = new ArrayList<>();
            hospitalList.add("Fortis Hospital");
            hospitalList.add("Aster CMI Hospital");
            hospitalList.add("Narayana Multispecialily Hospital");
            hospitalList.add("Fortis La Femme");
            hospitalList.add("Fortis Hospital");
            hospitalList.add("Manipal North Side Hospital");
            hospitalList.add("Sakra World Hospital");
            hospitalList.add("Columbia Asia Hospital");
            hospitalList.add("Apollo Speciality Hospital");
            hospitalList.add("Prashanth Hospital");
            hospitalList.add("Sakra World Hospital");
            hospitalList.add("Columbia Asia Hospital");
            hospitalList.add("GVG Invivo Hospitals");
            hospitalList.add("Ananya Hospital Pvt Ltd");
            hospitalList.add("Anugraha Vittala Hospital");
            policy1.setCashlessHospitals(hospitalList);
        }
        else {
            policy1.setCashlessHospitals(policy.getCashlessHospitals());
        }
        if(policy.getDiseasesCovered().isEmpty()){
            List<String> diseasesCovered = new ArrayList<>();
            diseasesCovered.add("Cancer");
            diseasesCovered.add("AIDS");
            diseasesCovered.add("Diabetes");
            policy1.setDiseasesCovered(diseasesCovered);
        }
        else {
            List<String> diseasesCovered;
            diseasesCovered = Arrays.asList(policy.getDiseasesCovered().split(","));
            policy1.setDiseasesCovered(diseasesCovered);
        }

        policyRepository.save(policy1);
        Optional<Policy> policy3 = policyRepository.findByUniqueId(policy1.getUniqueId());
        if(policy3.isPresent()) {
            kafkaTemplate.send("policy_added",policy3.get());
            policyGlobal =  policy3.get();
        }
        return policyGlobal;
    }

    //Method for deleting the policy for insurer
    @Override
    public Policy deletePolicy(String insurerName, String policyName) throws PolicyNotFoundException {
        if(policyRepository.existsByPolicyNameAndInsurerName(policyName,insurerName)) {
            String uniqueId = insurerName + policyName;
            Optional<Policy> policy2 = policyRepository.findByUniqueId(uniqueId);
            if(policy2.isPresent())
            {
                Policy policy = policy2.get();
                Policy policy1 = policy;
                policyRepository.delete(policy);
                kafkaTemplate.send("policy_deleted",policy1);

                return policy1;
            }

        }
        throw policyNotFoundException;
    }
    //Method for getting the policy for insurer
    @Override
    public List<Policy> getPolicy(String insurerName) throws InsurerNotFoundException{
        if(policyRepository.existsByInsurerName(insurerName)) {
            Optional<List<Policy>> policy = policyRepository.findByInsurerName(insurerName);
            if(policy.isPresent())
            {
                policyListGlobal= policy.get();
                return policyListGlobal;
            }
        }
        else {
            throw insurerNotFoundException;
        }
        return policyListGlobal;
    }

    //Method to find policy based on insurerName and policyName
    @Override
    public Policy getPolicyForUser(String insurerName, String policyName)
            throws InsurerNotFoundException,PolicyNotFoundException {

        if (policyRepository.existsByInsurerName(insurerName)) {
            if (policyRepository.existsByPolicyNameAndInsurerName(policyName, insurerName)) {
                Optional<Policy> policy1 = policyRepository.findByInsurerNameAndPolicyName(insurerName, policyName);
                if(policy1.isPresent())
                {
                    policyGlobal = policy1.get();
                    return policyGlobal;
                }

            }
            else  {
                throw policyNotFoundException;
            }
        }
        else {
            throw insurerNotFoundException;
        }
        return policyGlobal;
    }

    //Method for adding the inusredName
    @Override
    public Policy addInsured(String insurerName, String policyName, String insuredName)
            throws InsurerNotFoundException,PolicyNotFoundException{
        if(policyRepository.existsByInsurerName(insurerName)) {
            if(policyRepository.existsByPolicyNameAndInsurerName(policyName,insurerName)) {
                Optional<Policy> policy1 = policyRepository.findByUniqueId(insurerName+policyName);
                if(policy1.isPresent()) {
                    policyGlobal = policy1.get();
                    List<String> insuredList = policyGlobal.getInsuredList();
                    insuredList.add(insuredName);
                    policyGlobal.setInsuredList(insuredList);
                    policyRepository.save(policyGlobal);
                    Optional<Policy> policy2 = policyRepository.findByInsurerNameAndPolicyName(insurerName,policyName);
                    if(policy2.isPresent()) {
                        policyGlobal = policy2.get();
                        return policyGlobal;
                    }
                }
            }
            else {
                throw policyNotFoundException;
            }
        }
        else {
            throw insurerNotFoundException;
        }
        return policyGlobal;
    }

    //Method for deleting the insuredName from the policy
    @Override
    public Policy deleteInsured(String insurerName, String policyName, String insuredName)
            throws InsurerNotFoundException,PolicyNotFoundException{
        if(policyRepository.existsByInsurerName(insurerName)) {
            if(policyRepository.existsByPolicyNameAndInsurerName(policyName,insurerName)) {
                Optional<Policy> policy1 = policyRepository.findByUniqueId(insurerName+policyName);
                if(policy1.isPresent()) {
                    policyGlobal = policy1.get();
                    List<String> insuredList = policyGlobal.getInsuredList();
                    insuredList.remove(insuredName);
                    policyGlobal.setInsuredList(insuredList);
                    policyRepository.save(policyGlobal);
                    Optional<Policy> policy2 = policyRepository.findByInsurerNameAndPolicyName(insurerName,policyName);
                    if(policy2.isPresent()) {
                        policyGlobal = policy2.get();
                    }
                }
            }
            else {
                throw policyNotFoundException;
            }
        }
        else {
            throw insurerNotFoundException;
        }
        return policyGlobal;
    }

    //Method to get the policies of the User
    @Override
    public List<Policy> getPoliciesOfUser(String insuredName)
            throws InsuredPoliciesNotFoundException {
        policyListGlobal = policyRepository.findAll();
        List<Policy> insuredPolicies = new ArrayList<>();
        for(int i = 0; i < policyListGlobal.size(); i++) {
            Policy policy = policyListGlobal.get(i);
            if(policy.getInsuredList().contains(insuredName)) {
                insuredPolicies.add(policy);
            }
        }
        if(insuredPolicies.isEmpty()) {
            throw insuredPoliciesNotFoundException;
        }
        else {
            return insuredPolicies;
        }
    }

    //Method to get Insurer Names
    @Override
    public Set<String> getInsurerList() {
        Set<String> set = new HashSet<>();
        List<Policy> policies = policyRepository.findAll();
        for(int i = 0; i < policies.size(); i++) {
            set.add(policies.get(i).getInsurerName());
        }
        return set;
    }

    public Policy getPolicyByPolicyName(String policyName) throws PolicyNotFoundException {
        Optional<Policy> policy= policyRepository.findByPolicyName(policyName);
        if(policy.isPresent()) {
            return policy.get();
        }
        else {
            throw new PolicyNotFoundException();
        }
    }
    //Method to get all policies
    public List<Policy> getAllPolicies(){
        return policyRepository.findAll();
    }
}
