package com.stackroute.pie.service;

import com.stackroute.pie.domain.Policy;
import com.stackroute.pie.domain.SearchPDM;
import com.stackroute.pie.repository.SearchRepository;
import com.stackroute.pie.repository.SearchValueRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


public class SearchServiceImplTest {

    private Optional optional;
    @Mock
    SearchRepository searchRepository;

    @Mock
    SearchValueRepository searchValueRepository;

    @InjectMocks
    SearchServiceImpl searchService;

    Policy policy;
    List<Policy> policy1;
    List<String> diseasesCovered;
    List<String> cashlessHospitals;
    String companyString;
    String policyString;
    String diseaseString;
    SearchPDM searchPDM;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        companyString = "maxlife,starmax,futurelife,happylife,sunlife";
        policyString = "starmaxchildcare,starmaxoptima,starmaxeasyhealth,sunlifechildcare,sunlifeoptima,sunlifeeasyhealth,happylifechildcare,happylifeoptima,happylifeeasyhealth,futurelifechildcare,futurelifeeasyhealth,futurelifeoptima,maxlifechildcare,maxlifeoptima,maxlifeeasyhealth";
        diseaseString = "cancer, diabetes, aids, dengue, malaria, tuberculosis, cardiac, heartattack, surgery";
        policy1 = new ArrayList<>();
        diseasesCovered = new ArrayList<>();
        diseasesCovered.add("cancer");
        diseasesCovered.add("cardiac");
        policy = new Policy(40,
                "starmaxeasyhealth",
                "starmax",
                "group",
                "Apollo",
                3, diseasesCovered,
                cashlessHospitals,
                6,
                "both",
                "Apollo",
                "Apollo policy",
                5000,
                60000,
                null,
                10,
                30,
                null,
                null, "https://yt3.ggpht.com/a-/AAuE7mB0lLHs75vGKU5SWS1YFplzmogoljQvy9j_LA=s900-mo-c-c0xffffffff-rj-k-no", 10000);
        policy1.add(policy);
        optional = Optional.of(policy);
    }

        @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllPolicies() {
        when(searchRepository.findAll()).thenReturn(policy1);
        List<Policy> actual=searchService.getAllPolicies("cancer");
        assertEquals(policy1,actual);
        verify(searchRepository, Mockito.times(1)).findAll();
//        verifyNoMoreInteractions(trackService);
    }


    @Test
    public void getAllPolicies1() {
        when(searchRepository.findAll()).thenReturn(policy1);
        List<Policy> actual=searchService.getAllPolicies("cancer");
        assertEquals(policy1,actual);
        verify(searchRepository, Mockito.times(1)).findAll();
//        verifyNoMoreInteractions(trackService);
    }

    @Test
    public void savePolicy() {
        when(searchRepository.save(policy)).thenReturn(policy);
        Policy actual = searchService.savePolicy(policy);
        assertEquals(policy, actual);
        verify(searchRepository, times(1)).save(policy);

    }

    @Test
    public void tokenString() throws Exception {
        int ag=30;
        if(ag<100)
            when( searchRepository.findAll()).thenReturn(policy1);
        List<Policy> list = new ArrayList<>();
        for(Policy p:policy1) {
            if (p.getMinAge() < ag && p.getMaxAge() > ag) {
                list.add(p);
            }
        }
        List<Policy> actual6=searchService.tokenString("30");
        assertEquals(list,actual6);

        int sum=3000;
        if(sum>1000)
            when( searchRepository.findAll()).thenReturn(policy1);
        List<Policy> list1 = new ArrayList<>();
        for(Policy p:policy1) {
            if (p.getMinSumInsured()<=sum && p.getMaxSumInsured()>=sum) {
                list1.add(p);
            }
        }
        List<Policy> actual7=searchService.tokenString("3000");
        assertEquals(list1,actual7);

        if(companyString.contains("starmax"))
            when( searchRepository.findByInsurerName("starmax")).thenReturn(policy1);
        List<Policy> actual8=searchService.tokenString("starmax");
        assertEquals(policy1,actual8);

        if(policyString.contains("starmaxeasyhealth"))
            when( searchRepository.findByPolicyName("starmaxeasyhealth")).thenReturn(policy1);
        List<Policy> actual9=searchService.tokenString("starmax");
        assertEquals(policy1,actual9);

        if(diseaseString.contains("cancer"))
            when( searchRepository.findAll()).thenReturn(policy1);
        List<Policy> p1= new ArrayList<>();
        for(Policy t:policy1)
        {
            if(t.getDiseasesCovered().contains("cancer")){
                p1.add(t);
            }
        }
        List<Policy> actual10=searchService.tokenString("cancer");
        assertEquals(p1,actual10);

        if(policyString.contains("starmaxeasyhealth") && diseaseString.contains("cancer"))
            when( searchRepository.findAll()).thenReturn(policy1);
        List<Policy> addPolicy =new ArrayList<>();
        for(Policy p:policy1){
            if(p.getPolicyName().equals("starmaxeasyhealth") && p.getDiseasesCovered().contains("cancer")){
                addPolicy.add(p);
            }
        }
        List<Policy> actual=searchService.tokenString("cancer starmaxeasyhealth");
        assertEquals(addPolicy,actual);

        if(companyString.contains("starmax") && diseaseString.contains("cancer"))
            when( searchRepository.findAll()).thenReturn(policy1);
        List<Policy> addPolicy1 =new ArrayList<>();
        for(Policy p:policy1){
            if(p.getInsurerName().equals("starmax") && p.getDiseasesCovered().contains("cancer")){
                addPolicy1.add(p);
            }

        }
        List<Policy> actual1=searchService.tokenString("starmax cancer");
        assertEquals(addPolicy1,actual1);

        if(companyString.contains("starmax"))
            when( searchRepository.findByInsurerName("starmax")).thenReturn(policy1);
        List<Policy> addPolicy2 =new ArrayList<>();
        for(Policy p:policy1){
            if(p.getPolicyName().equals("starmaxeasyhealth")){
                addPolicy2.add(p);
            }
        }
        List<Policy> actual2=searchService.tokenString("starmaxeasyhealth starmax");
        assertEquals(addPolicy2,actual2);

        if(policyString.contains("starmaxeasyhealth"))
            when( searchRepository.findByPolicyName("starmaxeasyhealth")).thenReturn(policy1);
        List<Policy> addPolicy3 =new ArrayList<>();
        for(Policy p:policy1){
            if(p.getInsurerName().equals("starmax")){
                addPolicy3.add(p);
            }
        }
        List<Policy> actual3=searchService.tokenString("starmax starmaxeasyhealth");
        assertEquals(addPolicy3,actual3);

        int num=4000;
        if(policyString.contains("starmaxeasyhealth") && num>1000)
            when( searchRepository.findByPolicyName("starmaxeasyhealth")).thenReturn(policy1);
        List<Policy> addPolicy4 =new ArrayList<>();
        for(Policy p:policy1){
            if(p.getMinSumInsured()<=num && p.getMaxSumInsured()>= num){
                addPolicy4.add(p);
            }
        }
        List<Policy> actual4=searchService.tokenString(" starmaxeasyhealth  4000");
        assertEquals(addPolicy4,actual4);

        int age=30;
        if(policyString.contains("starmaxeasyhealth") && age<100)
            when( searchRepository.findByPolicyName("starmaxeasyhealth")).thenReturn(policy1);
        List<Policy> addPolicy5 =new ArrayList<>();
        for(Policy p:policy1){
            if(p.getMinAge()<age && p.getMaxAge()>age){
                addPolicy5.add(p);
            }
        }
        List<Policy> actual5=searchService.tokenString(" starmaxeasyhealth  30");
        assertEquals(addPolicy5,actual5);
    }

}