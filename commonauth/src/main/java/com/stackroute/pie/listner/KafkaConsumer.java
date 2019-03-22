package com.stackroute.pie.listner;

import com.stackroute.pie.domain.*;
import com.stackroute.pie.repository.CommonAuthRepository;
import com.stackroute.pie.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class KafkaConsumer {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CommonAuthRepository commonAuthRepository;

 //consuming data from kafka & setting it to commonauth
    @KafkaListener(topics = "userregg_json", groupId = "group2_json", containerFactory = "userKafkaListenerFactory")
    public void consumeJson(Insured user) {

        Set<Role> roles = new HashSet<>();
        Role insurerRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Insured Role not find."));
        roles.add(insurerRole);
        user.setRoles(roles);
        CommonAuth commonAuth = new CommonAuth();
        commonAuth.setRoles(user.getRoles());
        commonAuth.setEmail(user.getEmail());
        commonAuth.setName(user.getName());
        commonAuth.setPassword(user.getPassword());
        commonAuth.setRoles(roles);
        commonAuth.setUsername(user.getUsername());
        commonAuthRepository.save(commonAuth);



    }

    @KafkaListener(topics = "company_1_json", groupId = "group2_json", containerFactory = "userKafkaListenerFactory")
    public void consumeJson(Insurer user) {
        Set<Role> roles = new HashSet<>();
        Role insurerRole = roleRepository.findByName(RoleName.ROLE_INSURER)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Insured Role not find."));
        roles.add(insurerRole);
        user.setRoles(roles);
        CommonAuth commonAuth = new CommonAuth();
        commonAuth.setRoles(user.getRoles());
        commonAuth.setEmail(user.getInsurerEmail());
        commonAuth.setName(user.getInsurerName());
        commonAuth.setPassword(user.getPassword());
        commonAuth.setRoles(roles);
        commonAuth.setUsername(user.getInsurerName());
        commonAuthRepository.save(commonAuth);
    }





}
