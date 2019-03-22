package com.stackroute.pie.controller;

import com.stackroute.pie.domain.FamilyMembers;
import com.stackroute.pie.domain.Insured;
import com.stackroute.pie.domain.Insurer;
import com.stackroute.pie.domain.Policy;
import com.stackroute.pie.service.RecommendationsServImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("api/v1/")
@CrossOrigin("*")
public class RecommendationsController {


    private RecommendationsServImpl recommendationsServ;
    @Autowired
    public RecommendationsController(KafkaTemplate<String, List<Policy>> kafkaTemplate, RecommendationsServImpl recommendationsServ){

        this.recommendationsServ = recommendationsServ;
    }


    @PostMapping("insurer")
    public ResponseEntity saveInsurer(@RequestBody Insurer insurer){
        ResponseEntity responseEntity;
        Insurer insurer1=recommendationsServ.createInsurer(insurer);
        responseEntity= new ResponseEntity<Insurer>(insurer1, HttpStatus.CREATED);
        return responseEntity;
    }




    @PostMapping("policy")
    public ResponseEntity savePolicy(@RequestBody Policy policy){
        ResponseEntity responseEntity;
        Policy policy1=recommendationsServ.createPolicy(policy);
        responseEntity= new ResponseEntity<Policy>(policy1, HttpStatus.CREATED);
        return responseEntity;
    }

    @PostMapping("insured")
    public ResponseEntity saveInsured(@RequestBody Insured insured){
        ResponseEntity responseEntity;
        Insured insured1=recommendationsServ.createInsured(insured);
        responseEntity= new ResponseEntity<Insured>(insured1, HttpStatus.CREATED);
        return responseEntity;
    }

    @PostMapping("members")
    public ResponseEntity saveMember(@RequestBody FamilyMembers familyMembers){
        ResponseEntity responseEntity;
        FamilyMembers familyMembers1=recommendationsServ.createMembers(familyMembers);
        responseEntity= new ResponseEntity<FamilyMembers>(familyMembers1, HttpStatus.CREATED);
        return responseEntity;
    }


    @DeleteMapping("insurerDelete")
    public ResponseEntity deleteInsurer(@RequestBody Insurer insurer){
        ResponseEntity responseEntity;
        Insurer insurer1=recommendationsServ.deleteInsurer(insurer);
        responseEntity= new ResponseEntity<Insurer>(insurer1, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("policyDelete")
    public ResponseEntity deletePolicy(@RequestBody Policy policy){
        ResponseEntity responseEntity;
        Policy policy1=recommendationsServ.deletePolicy(policy);
        responseEntity= new ResponseEntity<Policy>(policy1, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("insuredDelete")
    public ResponseEntity deleteInsured(@RequestBody Insured insured){
        ResponseEntity responseEntity;
        Insured insured1=recommendationsServ.deleteInsured(insured);
        responseEntity= new ResponseEntity<Insured>(insured1, HttpStatus.OK);
        return responseEntity;
    }


    @PostMapping(value = "linkPolicy/{insurerName}/{policyId}")
    public ResponseEntity linkpolicy(@PathVariable String insurerName,@PathVariable Long policyId){
        ResponseEntity responseEntity;
        responseEntity= new ResponseEntity<String>(recommendationsServ.insurerPolicy(insurerName,policyId),HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping(value = "linkInsured/{policyId}/{username}")
    public ResponseEntity linkinsured(@PathVariable Long policyId,@PathVariable String username){
        ResponseEntity responseEntity;
        responseEntity= new ResponseEntity<String>(recommendationsServ.insuredPolicy(policyId,username),HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping(value = "view/{policyId}/{username}")
    public ResponseEntity viewPolicy(@PathVariable Long policyId,@PathVariable String username){
        ResponseEntity responseEntity;
        responseEntity= new ResponseEntity<String>(recommendationsServ.viewPolicy(policyId,username),HttpStatus.CREATED);
        return responseEntity;
    }

    @PostMapping(value = "dependants/{memberName}/{username}")
    public ResponseEntity linkDependants(@PathVariable String memberName,@PathVariable String username){
        ResponseEntity responseEntity;
        responseEntity= new ResponseEntity<String>(recommendationsServ.linkDependants(memberName,username),HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("policies")
    public List<Policy> getAll(){
        return recommendationsServ.displayPolicy();
    }

    @GetMapping("user/{username}")
    public Insured geUser(@PathVariable("username")String username){
        return recommendationsServ.findUser(username);

    }

    @GetMapping("policyByAgeGender/{username}")
    public  List<Policy> getByAgeGender(@PathVariable("username")String username){
          return recommendationsServ.getByAgeGender(username);

    }
    @GetMapping("policyByAge/{username}")
    public  List<Policy> getByAge(@PathVariable("username")String username){
         return recommendationsServ.getByAge(username);

    }

    @GetMapping("policyByGender/{username}")
    public  List<Policy> getByGender(@PathVariable("username")String username){
        return recommendationsServ.getByGender(username);

    }

    @GetMapping("policyByDisease/{username}")
    public  List<Policy> getByDisease(@PathVariable("username")String username){
        return recommendationsServ.getByDisease(username);

    }


    @GetMapping("policyByAgeGenderDisease/{username}")
    public  List<Policy> getByAgeGenderDisease(@PathVariable("username")String username){
        return recommendationsServ.getByAgeGenderDisease(username);

    }

    @GetMapping("policyForDependants/{username}")
    public  List<Policy> getForDependants(@PathVariable("username")String username){
        return recommendationsServ.policyForDependants(username);

    }
}
