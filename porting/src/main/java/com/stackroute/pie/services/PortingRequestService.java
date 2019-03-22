package com.stackroute.pie.services;

import com.stackroute.pie.domain.PortingRequest;
import com.stackroute.pie.exceptions.RequestNotFoundException;

import java.util.List;
import java.util.Optional;

public interface PortingRequestService {
    public PortingRequest postRequest(PortingRequest request);
    public PortingRequest updateRequest(PortingRequest request) throws RequestNotFoundException;
    public PortingRequest deleteRequest(int requestId) throws RequestNotFoundException;
    public List<PortingRequest> getRequests(String insuredName) throws RequestNotFoundException;
    Optional<PortingRequest> getPortingRequestByPortingRequestId(int portingRequestId);
    public List<PortingRequest> getRequestsforInsurer(String insurerName) throws RequestNotFoundException;

}
