package com.stackroute.pie.controller;

import com.stackroute.pie.domain.*;
import com.stackroute.pie.repository.UserRepository;
import com.stackroute.pie.exceptions.UserNotFoundException;
import com.stackroute.pie.message.request.SignUpForm;
import com.stackroute.pie.message.response.ResponseMessage;
import com.stackroute.pie.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    UserController(KafkaTemplate<String, Insured> kafkaTemplate,
                   UserRepository userRepository,
                   PasswordEncoder encoder,
                   UserDetailsServiceImpl userService)
    {
        this.kafkaTemplate = kafkaTemplate;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.userService = userService;
    }

    private KafkaTemplate<String, Insured> kafkaTemplate;

    private UserRepository userRepository;

    private PasswordEncoder encoder;

    private UserDetailsServiceImpl userService;

    @PostMapping("/signup")
    public ResponseEntity registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        // Creating insured's account
        Insured insured = new Insured(signUpRequest.getFullName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getGender(), signUpRequest.getCreatedDate(), signUpRequest.getSecurityAnswer(), signUpRequest.getAge(),signUpRequest.getFamilyMembers());
        Set<Role> roles = new HashSet<>();
        Role userrRole = new Role();
        userrRole.setName(RoleName.ROLE_USER);
        roles.add(userrRole);
        insured.setRoles(roles);
        userRepository.save(insured);
        kafkaTemplate.send("userregg_json", insured);
        return new ResponseEntity(new ResponseMessage("Insured registered successfully!"), HttpStatus.OK);
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity user(@PathVariable("username") String username) {
        ResponseEntity responseEntity;
        try {
            Insured insured1 = userService.getProfile(username);
            responseEntity = new ResponseEntity(insured1, HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("/profile/{username}")
    public ResponseEntity user(@PathVariable("username") String username, @RequestBody Insured user) {
        ResponseEntity responseEntity;
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            Insured insured1 = userService.updateProfile(user);
            responseEntity = new ResponseEntity(insured1, HttpStatus.OK);

        } catch (UserNotFoundException ex) {
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("profile/familymembers")
    public ResponseEntity addFamilyMembers(@RequestBody FamilyMembers familyMembers) throws UserNotFoundException {
        Insured insured1 = new Insured();
        ResponseEntity responseEntity;
        try {
            insured1 = userService.addFamilyMembers(familyMembers);
            responseEntity = new ResponseEntity(insured1, HttpStatus.CREATED);

        } catch (UserNotFoundException ex) {
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
        }
        kafkaTemplate.send("family_json", insured1);
        return responseEntity;
    }
    @GetMapping("/email/{username}")
    public ResponseEntity<String> getUserEmail(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserEmail(username), HttpStatus.OK);
    }
}



