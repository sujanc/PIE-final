package com.stackroute.pie.repository;

import com.stackroute.pie.domain.BuyPolicy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BuyPolicyRepository extends MongoRepository<BuyPolicy,Integer>{

}
