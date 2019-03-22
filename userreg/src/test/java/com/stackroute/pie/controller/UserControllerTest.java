package com.stackroute.pie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.stackroute.pie.domain.FamilyMembers;
import com.stackroute.pie.domain.Insured;
import com.stackroute.pie.message.request.SignUpForm;
import com.stackroute.pie.repository.UserRepository;
import com.stackroute.pie.security.jwt.JwtAuthEntryPoint;
import com.stackroute.pie.security.jwt.JwtAuthTokenFilter;
import com.stackroute.pie.security.jwt.JwtProvider;
import com.stackroute.pie.services.UserDetailsServiceImpl;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMVC;
    @MockBean
    private UserDetailsServiceImpl userProfileService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    JwtAuthEntryPoint jwtAuthEntryPoint;

    @MockBean
    JwtProvider jwtProvider;

    @MockBean
    JwtAuthTokenFilter jwtAuthTokenFilter;

//        @MockBean
//    WebSecurityConfig webSecurityConfig;

    @MockBean
    private KafkaTemplate<String, Insured> kafkaTemplate;

    @InjectMocks
    private UserController userController;

    Insured user;

    SignUpForm signUpForm;
    private int insuredId;
    private Insured user1;
    List<FamilyMembers> familyMembers;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMVC = MockMvcBuilders.standaloneSetup(userController).build();
        signUpForm = new SignUpForm();
        signUpForm.setAge(22);
        signUpForm.setEmail("saurabh@gmail.com");
        signUpForm.setFullName("saurabh");
        signUpForm.setGender("male");
        signUpForm.setPassword("qwertyu");
        signUpForm.setSecurityAnswer("food");
        signUpForm.setCreatedDate(new Date());
        signUpForm.setUsername("qwertyu");
        signUpForm.setFamilyMembers(familyMembers);
        Insured  user =new Insured(signUpForm.getFullName(),signUpForm.getUsername(),signUpForm.getEmail(),signUpForm.getPassword(),signUpForm.getGender(),signUpForm.getCreatedDate(),signUpForm.getSecurityAnswer(),signUpForm.getAge(),signUpForm.getFamilyMembers());




        mockMVC = MockMvcBuilders.standaloneSetup(userController).build();
        user1 = new Insured(1,"anusha","anusha123","anusha@gmail.com","anusha",21,"female","cat");

    }



    @Test
    public void registerUser() throws Exception {
        String uri ="/api/auth/signup";
        this.mockMVC.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(signUpForm)))
                .andExpect(status().isOk());


    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


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



}
