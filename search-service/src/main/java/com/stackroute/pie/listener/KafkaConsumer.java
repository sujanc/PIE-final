package com.stackroute.pie.listener;

import com.stackroute.pie.domain.Policy;
import com.stackroute.pie.domain.PolicyList;
import com.stackroute.pie.domain.Search;
import com.stackroute.pie.repository.ChatBotRepository;
import com.stackroute.pie.repository.SearchRepository;
import com.stackroute.pie.service.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KafkaConsumer {

    @Autowired
    SearchRepository searchRepository;

    @Autowired
    SearchServiceImpl searchService;

    @Autowired
    ChatBotRepository chatBotRepository;


//    @KafkaListener(topics = "Kafka_Example_json", groupId = "group_json",
//            containerFactory = "userKafkaListenerFactory")

    //consuming data from kafka & setting it to commonauth
    @KafkaListener(topics = "policy_added", groupId = "group_json", containerFactory = "userKafkaListenerFactory")
    public void consumeJson(Policy policy) {
        searchRepository.save(policy);
    }

    @KafkaListener(topics = "searchJSON", groupId = "group8_json", containerFactory = "userKafkaListenerFactory")
    public void consumeJson1(Search search) {
        String var=search.getSearchString();
        try {
            List<Policy> serch= searchService.tokenString(var);
            chatBotRepository.deleteAll();
            for(Policy p:serch)
            {
                PolicyList chatPolicy = new PolicyList();
                chatPolicy.setPolicyId(p.getPolicyId());
                chatPolicy.setPolicyName(p.getPolicyName());
                chatPolicy.setDiseasesCovered(p.getDiseasesCovered());
                chatPolicy.setCashlessHospitals(p.getCashlessHospitals());
                chatPolicy.setGenderAvail(p.getGenderAvail());
                chatPolicy.setInsuredList(p.getInsuredList());
                chatPolicy.setInsurerLicense(p.getInsurerLicense());
                chatPolicy.setInsurerName(p.getInsurerName());
                chatPolicy.setMaxAge(p.getMaxAge());
                chatPolicy.setMinAge(p.getMinAge());
                chatPolicy.setMaxSumInsured(p.getMaxSumInsured());
                chatPolicy.setMinSumInsured(p.getMinSumInsured());
//                chatPolicy.setImageUrl(p.getImageUrl());
                chatBotRepository.save(chatPolicy);
            }


        }catch (Exception e)
        {
            e.getMessage();
        }


    }



    @KafkaListener(topics = "policy_deleted", groupId = "policy_groupp_json", containerFactory = "userKafkaListenerFactory")
    public void consumeJson2(Policy policy) {
        searchRepository.deleteByPolicyId(policy.getPolicyId());
    }


}








