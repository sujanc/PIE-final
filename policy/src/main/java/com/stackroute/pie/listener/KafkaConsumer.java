package com.stackroute.pie.listener;

import com.stackroute.pie.domain.BuyPolicy;
import com.stackroute.pie.domain.PortingRequest;
import com.stackroute.pie.exceptions.InsurerNotFoundException;
import com.stackroute.pie.exceptions.PolicyNotFoundException;
import com.stackroute.pie.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    PolicyService policyService;
    @Autowired
    KafkaConsumer(PolicyService policyService) {
        this.policyService = policyService;
    }
    @KafkaListener(topics = "incomingporting", groupId = "group_porting_json", containerFactory = "userKafkaListenerFactory")
    public void consumeJson(PortingRequest portingRequest) throws InsurerNotFoundException, PolicyNotFoundException {
        String insurerX = portingRequest.getInsurerName();
        String insurerY = portingRequest.getNewInsurerName();
        String policyX = portingRequest.getInsurerProduct();
        String policyY = portingRequest.getNewInsurerProduct();
        String insuredName = portingRequest.getInsuredName();

        policyService.deleteInsured(insurerX,policyX,insuredName);
        policyService.addInsured(insurerY,policyY,insuredName);

    }

    @KafkaListener(topics = "Buy_Policy", groupId = "group_buypolicy_json", containerFactory = "buyKafkaListenerFactory")
    public void consumeJsonBuyPolicy(BuyPolicy buyPolicy) throws InsurerNotFoundException, PolicyNotFoundException {
        if(!buyPolicy.getUsername().isBlank()) {
            policyService.addInsured(buyPolicy.getInsurerName(),buyPolicy.getPolicyName(),buyPolicy.getUsername());
        }
    }
}
