package com.stackroute.pie.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.pie.domain.Insurer;
import com.stackroute.pie.domain.PremiumCalci;
import com.stackroute.pie.repository.InsurerRepository;
import com.stackroute.pie.services.InsurerServiceImpl;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(InsurerController.class)
public class InsurerControllerTest {
    @Autowired
    private MockMvc mockMVC;

    @InjectMocks
    private InsurerController insurerController;

    @MockBean
    private InsurerServiceImpl insurerService;


    @MockBean
    private InsurerRepository insurerRepository;

    @MockBean
    private KafkaTemplate<String, Insurer> kafkaTemplate;


    private Insurer insurer;

    private PremiumCalci premiumCalci;

    List<String> insuredList = new ArrayList<>();
    List<String> diseases = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMVC = MockMvcBuilders.standaloneSetup(insurerController).build();
        insurer = new Insurer("maxbupa","maxbupa","maxbupa@gmail.com","maxbupa","bangalore","whats your favourite food","pizza");
        premiumCalci = new PremiumCalci("maxbupa","noone",1000,"Bangalore",40,2,1,4);
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
    public void registerUser() throws Exception {
        this.mockMVC.perform(post("/api/v1/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonToString(insurer)))
                .andExpect(status().isCreated());
    }

    @Test
    public void calculatePremium() throws Exception {
        this.mockMVC.perform(post("/api/v1/policy/premium/calculator")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonToString(premiumCalci)))
                .andExpect(status().isOk());
    }



}
