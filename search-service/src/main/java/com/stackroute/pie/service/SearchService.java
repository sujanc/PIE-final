package com.stackroute.pie.service;


import com.stackroute.pie.domain.Policy;
import com.stackroute.pie.exception.PolicyNotFoundException;

import java.io.IOException;
import java.util.List;

public interface SearchService {
    public List<Policy> getAllPolicies(String value);
    public Policy savePolicy(Policy policy);
    public List<Policy> tokenString(String value) throws IOException, PolicyNotFoundException;
    public List<Policy> getByDisease(String disease);
}
