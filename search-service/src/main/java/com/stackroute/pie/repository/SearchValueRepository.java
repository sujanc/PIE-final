package com.stackroute.pie.repository;

import com.stackroute.pie.domain.SearchPDM;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SearchValueRepository extends MongoRepository<SearchPDM,String> {

    SearchPDM save(SearchPDM searchPDM);
    SearchPDM findBySearchValue(String searchValue);
}
