package com.stackroute.pie.controller;

import com.stackroute.pie.message.request.LoginForm;
import com.stackroute.pie.message.response.JwtResponse;

import com.stackroute.pie.security.jwt.JwtProvider;
import com.stackroute.pie.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;


    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), (Collection<GrantedAuthority>) userDetails.getAuthorities()));

    }

    @GetMapping("/count/{insurerName}")
    public ResponseEntity getCount(@PathVariable(value = "insurerName") String insurerName) {

        Integer count  = userDetailsServiceImpl.getCount(insurerName);
        return new ResponseEntity(count, HttpStatus.OK);
    }


    @PostMapping("/count/update/{insurerName}")
    public ResponseEntity updateCount(@PathVariable(value = "insurerName") String insurerName) {
        Integer count = userDetailsServiceImpl.updateCount(insurerName);
        return new ResponseEntity(count,HttpStatus.OK);
    }


    }

