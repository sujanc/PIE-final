package com.stackroute.pie.repository;
import com.stackroute.pie.domain.InsurerPolicy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
 public interface ExternalDbRepository extends MongoRepository<InsurerPolicy,String> {


      void deleteByInsurerName(String insurerName);
      List<InsurerPolicy> findByInsurerName(String insurerName);


}
