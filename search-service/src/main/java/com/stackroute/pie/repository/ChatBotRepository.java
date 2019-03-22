package com.stackroute.pie.repository;

import com.stackroute.pie.domain.Policy;
import com.stackroute.pie.domain.PolicyList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ChatBotRepository extends MongoRepository<PolicyList, Integer> {

    PolicyList save(List<Policy> chatPolicy);

    List<PolicyList> findAll();
}
