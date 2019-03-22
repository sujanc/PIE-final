package com.stackroute.pie.repository;

import com.stackroute.pie.domain.FamilyMembers;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FamilyMembersRepository extends MongoRepository<FamilyMembers,Integer> {

}
