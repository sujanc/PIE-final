package com.stackroute.pie.controller;

import com.stackroute.pie.domain.Search;
import com.stackroute.pie.service.SearchService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/")
public class SearchController {

    private SearchService searchService;

    @Autowired
    private KafkaTemplate<String, Search> kafkaTemplate;

    @Autowired
    public SearchController(SearchService searchService){

        this.searchService=searchService;
    }

    //rest end point for search
    @PostMapping("search")
    public Search search(@RequestBody String search) throws JSONException {
        JSONObject jsonObj = new JSONObject(search);
        JSONObject location=jsonObj.getJSONObject("queryResult");
        Search searchClass =new Search();
        searchClass.setSessionID(jsonObj.getString("responseId"));
        searchClass.setSearchString(location.getString("queryText"));
        searchService.saveSearchText(searchClass);
        kafkaTemplate.send("searchJSON",searchClass);
      return null;

    }
}
