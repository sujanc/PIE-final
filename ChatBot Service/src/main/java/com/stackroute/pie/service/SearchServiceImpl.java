package com.stackroute.pie.service;

import com.stackroute.pie.domain.Search;
import com.stackroute.pie.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService{

    private SearchRepository searchRepository;

    @Autowired
    public SearchServiceImpl(SearchRepository searchRepository){
        this.searchRepository=searchRepository;
    }

    @Override
    public Search saveSearchText(Search search){

        return searchRepository.save(search);

    }
}
