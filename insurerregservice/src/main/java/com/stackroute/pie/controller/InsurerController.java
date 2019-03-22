package com.stackroute.pie.controller;

import com.stackroute.pie.domain.*;
import com.stackroute.pie.message.request.SignUpForm;
import com.stackroute.pie.message.response.ResponseMessage;
import com.stackroute.pie.repository.InsurerRepository;
import com.stackroute.pie.services.InsurerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class InsurerController {

    @Autowired
    InsurerRepository insurerRepository;

    @Autowired
    InsurerServiceImpl insurerService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    ResponseMessage responseMessage = new ResponseMessage("Insurer not found");


    @Autowired
    private KafkaTemplate<String, Insurer> kafkaTemplate;

    //Method for registering a new Insurer
    @PostMapping("/signup")
    public ResponseEntity registerUser(@RequestBody SignUpForm signUpRequest) {
        if (insurerRepository.existsByInsurerLicense(signUpRequest.getInsurerLicense())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.CONFLICT);
        }

        if (insurerRepository.existsByInsurerEmail(signUpRequest.getInsurerEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.CONFLICT);
        }
        signUpRequest.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        //Calling InsurerService to add into the MongoDB
        Insurer insurer = insurerService.addInsurer(signUpRequest);
        //Kafka producing the Insurer pojo ,so that login microservice can consume it
        kafkaTemplate.send("company_1_json",insurer);
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    //Method to calculate the premium
    @PostMapping("/policy/premium/calculator")
    public ResponseEntity calculatePremium(@RequestBody PremiumCalci premiumCalci){
        long premium = insurerService.calculatePremium(premiumCalci);
        String pr = Long.toString(premium);
        return new ResponseEntity<>(pr, HttpStatus.OK);

    }
}
