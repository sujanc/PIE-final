package com.stackroute.pie.controller;

import com.stackroute.pie.domain.BuyPolicy;
import com.stackroute.pie.domain.InsurerPolicy;
import com.stackroute.pie.service.ExternalDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class ExternalDbController {

    private ExternalDbService externalDbService;

    @Autowired
    private KafkaTemplate<String, BuyPolicy> kafkaTemplate;

    @Autowired
    public ExternalDbController(ExternalDbService externalDbService) {
        this.externalDbService = externalDbService;
    }




    @GetMapping("/policy/external/{insurerName}")
    public ResponseEntity getPolicies(@PathVariable(value = "insurerName") String insurerName) throws UnsupportedEncodingException, SQLException, ClassNotFoundException {
       List<InsurerPolicy> insurer = externalDbService.getPolicies(insurerName);
        return new ResponseEntity<List<InsurerPolicy>>(insurer, HttpStatus.OK);
    }

    @PostMapping("/external/buypolicy")
    public ResponseEntity buypolicy(@RequestBody BuyPolicy buyPolicy) throws UnsupportedEncodingException, SQLException, ClassNotFoundException {
        BuyPolicy premium = externalDbService.buyPolicy(buyPolicy);
        kafkaTemplate.send("Buy_Policy", buyPolicy);
        return new ResponseEntity<BuyPolicy>(premium, HttpStatus.OK);

    }

}
