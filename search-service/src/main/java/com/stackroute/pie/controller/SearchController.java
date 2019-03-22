package com.stackroute.pie.controller;


import com.stackroute.pie.domain.Policy;
import com.stackroute.pie.domain.PolicyList;
import com.stackroute.pie.exception.PolicyNotFoundException;
import com.stackroute.pie.repository.ChatBotRepository;
import com.stackroute.pie.service.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/")
public class SearchController {
    private SearchServiceImpl searchService;
    @Autowired
    public  SearchController(SearchServiceImpl searchService){
        this.searchService = searchService;
    }

    @Autowired
    ChatBotRepository chatBotRepository;

    //save method (temporary purpose)
    @PostMapping(value = "policy")
    public ResponseEntity savePolicy(@RequestBody Policy policy)
    {
        ResponseEntity responseEntity;
        Policy policy1 = searchService.savePolicy(policy);
        responseEntity = new ResponseEntity(policy1, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(value = "polici/{value}")
    public ResponseEntity getDiseases(@PathVariable(value = "value") String value)
    {
        List<Policy> allPolicies = searchService.getByDisease(value);

        return new ResponseEntity (allPolicies, HttpStatus.OK);
    }
    //to get policyList based on searchvalue
    @GetMapping(value="policies/{value}")
    public ResponseEntity tokeniseString(@PathVariable(value = "value")String value)
    { ResponseEntity responseEntity;
        try {
            List<Policy> alltokens = searchService.tokenString(value);
            responseEntity= new ResponseEntity (alltokens, HttpStatus.OK);
        }
        catch(PolicyNotFoundException | IOException ex){
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @GetMapping(value="chatpolicy")
    public ResponseEntity getResults()
    { ResponseEntity responseEntity;
        try {
            List<PolicyList> policyLists = chatBotRepository.findAll();
            responseEntity= new ResponseEntity(policyLists, HttpStatus.OK);
        }
        catch(Exception ex){
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PostMapping(value = "policys")
    public ResponseEntity<?> savePol(@RequestBody PolicyList policy)
    {
        ResponseEntity responseEntity;
        PolicyList policyList=chatBotRepository.save(policy);
        responseEntity = new ResponseEntity<PolicyList>(policyList, HttpStatus.OK);
        return responseEntity;
    }
}
