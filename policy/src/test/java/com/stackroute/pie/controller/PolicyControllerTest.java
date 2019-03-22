package com.stackroute.pie.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.stackroute.pie.domain.Policy;

import com.stackroute.pie.repository.PolicyRepository;
import com.stackroute.pie.service.PolicyServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(PolicyController.class)
public class PolicyControllerTest {
    @Autowired
    private MockMvc mockMVC;

    @InjectMocks
    private PolicyController policyController;

    @MockBean
    private PolicyServiceImpl policyService;


    @MockBean
    private PolicyRepository policyRepository;

    @MockBean
    private KafkaTemplate<String, Policy> kafkaTemplate;


    private Policy policy;
    private List<String> diseasesCovered = new ArrayList<>();
    private List<String> cashlessHospitals = new ArrayList<>();
    private List<String> termsAndConditions = new ArrayList<>();
    private List<String> insuredList = new ArrayList<>();
    private List<String> paymentList = new ArrayList<>();
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMVC = MockMvcBuilders.standaloneSetup(policyController).build();
        policy = new Policy("maxbupacancer-policy","maxbupa","maxbupa","cancer-policy",
                12,10,70,"family", "male",1,10,
                1000, 10000,"this is cancer policy of maxbupa",
                diseasesCovered,cashlessHospitals,termsAndConditions,insuredList,paymentList,"image1.jpg");
        insuredList.add("stackroute");
        policy.setInsuredList(insuredList);
    }
    private static String jsonToString(final Object obj) throws JsonProcessingException {
        String result;
        try {

            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "Json processing error";
        }
        return result;

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void deletePolicy() throws Exception {
        this.mockMVC.perform(delete("/api/v1/policy/maxbupa/cancer-policy")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonToString(policy)))
                .andExpect(status().isOk());
    }

    @Test
    public void getPolicy() throws Exception {
        this.mockMVC.perform(get("/api/v1/policy/maxbupa")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonToString(policy)))
                .andExpect(status().isOk());
    }

    @Test
    public void getPolicyofInsured() throws Exception {
        this.mockMVC.perform(get("/api/v1/policy/stackroute")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonToString(policy)))
                .andExpect(status().isOk());
    }

    @Test
    public void getPolicyforInsured() throws Exception {
        this.mockMVC.perform(get("/api/v1/policy/maxbupa/maxbupa")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonToString(policy)))
                .andExpect(status().isOk());
    }

    @Test
    public void addInsured() throws Exception {
        this.mockMVC.perform(put("/api/v1/policy/maxbupa/maxbupa/stackroute1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonToString(policy)))
                .andExpect(status().isOk());
    }


    @Test
    public void deleteInsured() throws Exception {
        this.mockMVC.perform(delete("/api/v1/policy/maxbupa111111/maxbupa/stackroute")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonToString(policy)))
                .andExpect(status().isOk());
    }

    @Test
    public void getInsurerList() throws Exception {
        this.mockMVC.perform(get("/api/v1/policy")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonToString(policy)))
                .andExpect(status().isOk());
    }









}
