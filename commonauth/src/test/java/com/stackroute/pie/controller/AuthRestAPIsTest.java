package com.stackroute.pie.controller;
//
//import com.stackroute.pie.domain.CommonAuth;
//import com.stackroute.pie.domain.Insured;
//import com.stackroute.pie.repository.CommonAuthRepository;
//import com.stackroute.pie.security.jwt.JwtAuthEntryPoint;
//import com.stackroute.pie.security.jwt.JwtProvider;
//import com.stackroute.pie.services.UserDetailsServiceImpl;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.security.test.context.support.WithUserDetails;
//import org.springframework.security.web.FilterChainProxy;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import javax.annotation.Resource;
//
//import static org.junit.Assert.*;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//@ContextConfiguration
//
//public class AuthRestAPIsTest {
//
//    private MockMvc mvc;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @MockBean
//    private AuthenticationManager authenticationManager;
//
////    @MockBean
////    private JwtTokenUtil jwtTokenUtil;
//
//
//
//    @Before
//    public void setup() {
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//    }
//
//    @MockBean
//     private CommonAuthRepository userRepository;
//    @MockBean
//    private JwtProvider jwtProvider;
//
//    @MockBean
//    private UserDetailsServiceImpl userService;
//    @Autowired
////    @Qualifier("databaseUserService")
//    protected UserDetailsService userDetailsService;
//    @InjectMocks
//    private AuthRestAPIs authRestAPIs;
//
//    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    protected UsernamePasswordAuthenticationToken getPrincipal(String username) {
//
//        UserDetails user = this.userDetailsService.loadUserByUsername(username);
//
//        UsernamePasswordAuthenticationToken authentication =
//                new UsernamePasswordAuthenticationToken(
//                        user,
//                        user.getPassword(),
//                        user.getAuthorities());
//
//        return authentication;
//    }
//
//
//
//
//
//
//    @Test
//
//    public void authenticateUser() throws Exception{
//        mockMvc.perform(post("/api/auth/signin")
//                .contentType(MediaType.ALL)
//                .param("username", "manasa")
//                .param("password", "manasa")
//        )
//                .andExpect(status().isOk());
//
//    }
//
//
//}