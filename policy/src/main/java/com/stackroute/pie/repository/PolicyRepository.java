package com.stackroute.pie.repository;

import com.stackroute.pie.domain.Policy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


//Crud operations for POJO class
@Repository
public interface PolicyRepository extends MongoRepository<Policy, String> {
    boolean existsByInsurerName(String insurerName);
    boolean existsByInsurerLicense(String insurerLicense);
    boolean existsByPolicyIdAndInsurerName(long policyId,String insurerName);
    boolean existsByPolicyNameAndInsurerName(String policyName,String insurerName);
    Optional<List<Policy>> findByInsurerLicense(String insurerLicense);
    Optional<List<Policy>> findByInsurerName(String insurerName);
    Optional<Policy> findByInsurerNameAndPolicyName(String insurerName, String policyName);
    Optional<Policy> findByUniqueId(String uniqueId);
    Optional<Policy> findByPolicyName(String policyName);
}
