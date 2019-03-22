package com.stackroute.pie.repository;

import com.stackroute.pie.domain.Insurer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InsurerRepository extends MongoRepository<Insurer,Long> {
    Optional<Insurer> findByInsurerLicense(String insurerLicense);
    Boolean existsByInsurerLicense(String insurerLicense);
    Boolean existsByInsurerName(String insurerName);
    Boolean existsByInsurerEmail(String insurerEmail);
}
