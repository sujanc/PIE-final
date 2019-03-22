package com.stackroute.pie.repository;

import com.stackroute.pie.domain.FormFormat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends MongoRepository<FormFormat, Integer> {

    @Query
    FormFormat findByFormName(String formName);
    @Query
    FormFormat findById(int formId);

}
