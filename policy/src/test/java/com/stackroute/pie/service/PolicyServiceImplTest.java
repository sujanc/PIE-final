package com.stackroute.pie.service;

import com.stackroute.pie.domain.Policy;
import com.stackroute.pie.domain.PortingRequest;
import com.stackroute.pie.exceptions.InsurerNotFoundException;
import com.stackroute.pie.exceptions.PolicyAlreadyExistsException;
import com.stackroute.pie.repository.PolicyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PolicyServiceImplTest {
    private Optional optional;

    private Optional optional1;
    @Mock
    private PolicyRepository policyRepository;
    @InjectMocks
    private PolicyServiceImpl policyService;

    private Policy policy;
    private List<Policy> policyList = new ArrayList<>();
    private List<String> diseasesList = new ArrayList<>();
    private List<String> cashlessHospital = new ArrayList<>();
    private List<String> termsAndConditions = new ArrayList<>();
    private List<String> insuredList = new ArrayList<>();
    private List<String> paymentList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        policy = new Policy("maxbupa","maxbupa-license","aidspolicy",1,10,24,
                1000,100000, "this policy is for aids","family",
                "all",2,10,"amv");
        diseasesList.add("AIDS");
        cashlessHospital.add("WECARE");
        termsAndConditions.add("WE dont care");
        insuredList.add("DEATH");
        paymentList.add("MAnasa");
        policy.setDiseasesCovered(diseasesList);
        policy.setCashlessHospitals(cashlessHospital);
        policy.setInsuredList(insuredList);
        policy.setTermsAndConditions(termsAndConditions);
        policy.setPaymentList(paymentList);

        policyList.add(policy);
        policyList.add(1, new Policy("maxbupa","maxbupa-license","cancerpolicy",2,10,24,
                1000,100000, "this policy is for cancer","family",
                "all",2,10,"amv"));
        System.out.println(policy);
        System.out.println(policyList);
        optional = Optional.of(policy);
        optional1 = Optional.of(policyList);
    }

    @Test
    public void findRequest() throws PolicyAlreadyExistsException, InsurerNotFoundException {
        when(policyRepository.existsByInsurerName(policy.getInsurerName())).thenReturn(true);
        when(policyRepository.findByInsurerName(policy.getInsurerName())).thenReturn(optional1);
        List<Policy> expectedOutput = policyService.getPolicy(policy.getInsurerName());
        assertEquals(policyList, expectedOutput);
    }

}
