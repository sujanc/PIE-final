package com.stackroute.pie.service;

import com.stackroute.pie.domain.FamilyMembers;
import com.stackroute.pie.domain.Insured;
import com.stackroute.pie.domain.Insurer;
import com.stackroute.pie.domain.Policy;
import com.stackroute.pie.repository.RecommendationsRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class RecommendationsServImplTest {
    @Mock
    private RecommendationsRepo recommendationsRepo;

    @InjectMocks
    private  RecommendationsServImpl recommendationsServ;
    private Insurer insurer1;
    private Policy policy1;
    private List<String> diseases = new ArrayList<String>();
    private List<Policy> policyList = new ArrayList<>();
    private List<FamilyMembers> familyMembers=new ArrayList<>() ;
    private FamilyMembers familyMembers1;
    private List<String> cashlessHospitals = new ArrayList<>();
    private List<String> termsAndConditions = new ArrayList<>();
    private List<String> insuredList = new ArrayList<>();
    private List<String> paymentList = new ArrayList<>();


    private Insured insured1;



    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        diseases.add("Cancer");
        termsAndConditions.add("Policy term is valid for more than 10 years");
        insuredList.add("Tejaswini");
        paymentList.add("hshjgsk");
        cashlessHospitals.add("Appolo Hospital");
        familyMembers1 = new FamilyMembers("teju","manasa",22L,"sister","female");
        familyMembers.add(familyMembers1);

        insurer1=new Insurer(1L,"Starhealth Insurance Company","1INS001","starhealth@gmail.com","starpass","Bangalore","what is ypur favourite food","cake");

        policy1=new Policy(11L,"Starhealth Insurance Company+Starhealth Family Policy","Starhealth Insurance Company","INS001","Starhealth Family Policy",10,60,"family","female",2,5,1,2622,7383,"best policy",diseases,cashlessHospitals,termsAndConditions,insuredList,paymentList,"image1");
        insured1=new Insured(1,"tejaswinisrinivas","teju","teju@gmail.com","teju1997","female","cake",22L,"Cancer",familyMembers,1);


    }


    @Test
    public void createInsurer() {
        when(recommendationsRepo.newInsurer(1L,"Starhealth Insurance Company","1INS001")).thenReturn(insurer1);
        Insurer insurerSaved=recommendationsServ.createInsurer(insurer1);
        System.out.println(insurerSaved);
        assertEquals(insurerSaved,insurer1);
    }

    @Test
    public void createInsured() {
        when(recommendationsRepo.newInsured(1,"teju","female",22L,"Cancer",1)).thenReturn(insured1);
        Insured insuredSaved=recommendationsRepo.newInsured(1,"teju","female",22L,"Cancer",1);
//        assertEquals(insuredSaved,insured1);
        assertEquals(insuredSaved.toString(),insured1.toString());
    }

    @Test
    public void findUser1() {
        when(recommendationsRepo.findUser("teju")).thenReturn(insured1);
        Insured user=recommendationsServ.findUser("teju");
        assertEquals(user,insured1);
    }


    @Test
    public void createPolicy() {
        when(recommendationsRepo.newPolicy("Starhealth Insurance Company+Starhealth Family Policy",1L,"Starhealth Insurance Company","1INS001","Starhealth Family Policy",10,60,"female",diseases,"Family","This is the best policy in the family","image1.jpg")).thenReturn(policy1);
        Policy policy=recommendationsRepo.newPolicy("Starhealth Insurance Company+Starhealth Family Policy",1L,"Starhealth Insurance Company","1INS001","Starhealth Family Policy",10,60,"female",diseases,"Family","This is the best policy in the family","image1.jpg");
        assertEquals(policy.toString(),policy1.toString());
    }

    @Test
    public void deletePolicy() {
        when(recommendationsRepo.deletePolicy(1L)).thenReturn(policy1);
        Policy policy=recommendationsRepo.deletePolicy(1L);
        assertEquals(policy,policy1);
    }

    @Test
    public void deleteInsurer() {
        when(recommendationsRepo.deleteInsurer("1INS001")).thenReturn(insurer1);
        Insurer insurerSaved=recommendationsRepo.deleteInsurer("1INS001");
        assertEquals(insurerSaved,insurer1);
    }


    @Test
    public void deleteInsured() {
        when(recommendationsRepo.deleteInsured(1)).thenReturn(insured1);
        Insured insured=recommendationsRepo.deleteInsured(1);
        assertEquals(insured,insured1);
    }


    @Test
    public void updateInsurer() {
        when(recommendationsRepo.updateInsured(1,"teju","female",22L,"Cancer",1)).thenReturn(insured1);
        Insured insured=recommendationsRepo.updateInsured(1,"teju","female",22L,"Cancer",1);
        assertEquals(insured,insured1);
    }

    @Test
    public void updatePolicy() {
        when (recommendationsRepo.updatePolicy(11L,"Starhealth Insurance Company","Starhealth Family Policy",10,60,"female",diseases,"family")).thenReturn(policy1);
        Policy policy=recommendationsRepo.updatePolicy(11L,"Starhealth Insurance Company","Starhealth Family Policy",10,60,"female",diseases,"family");
        assertEquals(policy,policy1);
    }

    @Test
    public void updateInsured() {
        when(recommendationsRepo.updateInsured(1,"teju","female",22L,"Cancer",1)).thenReturn(insured1);
        Insured insured=recommendationsRepo.updateInsured(1,"teju","female",22L,"Cancer",1);
        assertEquals(insured,insured1);
    }
}

