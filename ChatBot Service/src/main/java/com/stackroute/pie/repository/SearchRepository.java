package com.stackroute.pie.repository;

import com.stackroute.pie.domain.Search;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends MongoRepository<Search, String> {
}
