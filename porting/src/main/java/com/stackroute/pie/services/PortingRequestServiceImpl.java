package com.stackroute.pie.services;

import com.stackroute.pie.domain.PortingRequest;
import com.stackroute.pie.exceptions.RequestNotFoundException;
import com.stackroute.pie.repository.PortingRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PortingRequestServiceImpl implements PortingRequestService{
    private PortingRequestRepository requestRepository;
    private PortingRequest request2;
    @Autowired
    public PortingRequestServiceImpl(PortingRequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }
    //To post a request
    public PortingRequest postRequest(PortingRequest request){
        PortingRequest portingRequest= requestRepository.findTopByOrderByPortingRequestIdDesc();
        if(portingRequest != null)
            request.setPortingRequestId(portingRequest.getPortingRequestId() + 1);
        else
            request.setPortingRequestId(0);
        requestRepository.save(request);
        return request;
    }

    //To edit the request details
    public PortingRequest updateRequest(PortingRequest request) throws RequestNotFoundException{
        PortingRequest request1;
        if(requestRepository.existsByPortingRequestId(request.getPortingRequestId()) ){
            requestRepository.deleteByPortingRequestId(request.getPortingRequestId());
            request1 = requestRepository.save(request);
            return request1;
        }
        else
            throw new RequestNotFoundException();
    }

    //To delete a request
    public PortingRequest deleteRequest(int requestId) throws RequestNotFoundException{
        Optional<PortingRequest> request1 = requestRepository.findByPortingRequestId(requestId);
        if(requestRepository.existsByPortingRequestId(requestId)){
            requestRepository.deleteByPortingRequestId(requestId);
        }
        else
            throw new RequestNotFoundException();
        if(request1.isPresent())
        {
            request2 = request1.get();
        }
        return request2;
    }

    //To display requests
    public List<PortingRequest> getRequests(String insuredName) throws RequestNotFoundException {
        if(requestRepository.existsByInsuredName(insuredName)) {
            return requestRepository.findByInsuredName(insuredName);
        }
        else
            throw new RequestNotFoundException();
    }

    @Override
    public Optional<PortingRequest> getPortingRequestByPortingRequestId(int portingRequestId) {
        return  requestRepository.findByPortingRequestId(portingRequestId);
    }

    @Override
    public List<PortingRequest> getRequestsforInsurer(String insurerName) {
        Optional<List<PortingRequest>> portingRequestsOptional = requestRepository.findByNewInsurerName(insurerName);
        Optional<List<PortingRequest>> portingRequestsOptional1 = requestRepository.findByInsurerName(insurerName);
        List<PortingRequest> portingRequests = new ArrayList<>();
        List<PortingRequest> portingRequests1 = new ArrayList<>();
        if(portingRequestsOptional.isPresent())
            portingRequests = portingRequestsOptional.get();
        if(portingRequestsOptional1.isPresent())
            portingRequests1 = portingRequestsOptional1.get();
        List<PortingRequest> portingRequests2 = new ArrayList<>();
        if(!portingRequests.isEmpty()) {
            for(int i = 0; i < portingRequests.size(); i++) {
                portingRequests2.add(portingRequests.get(i));
            }
        }
        if(!portingRequests1.isEmpty()) {
            for(int i = 0; i < portingRequests1.size(); i++) {
                portingRequests2.add(portingRequests1.get(i));
            }
        }
        return portingRequests2;
    }

    //To get incoming porting requests
    public List<PortingRequest> getIncomingPortingRequest(String newInsurerName) {
        List<PortingRequest> portingRequest1 = new ArrayList<>();
        Optional<List<PortingRequest>> portingRequests = requestRepository.findByNewInsurerName(newInsurerName);
        if(portingRequests.isPresent())
        {
            portingRequest1 = portingRequests.get();
        }
        return portingRequest1;
    }

    //To get outgoing requests
    public List<PortingRequest> getOutgoingPortingRequest(String insurerName) {
        List<PortingRequest> portingRequest1 = new ArrayList<>();
        Optional<List<PortingRequest>> portingRequests = requestRepository.findByInsurerName(insurerName);
        if(portingRequests.isPresent())
        {
            portingRequest1 = portingRequests.get();
        }
        return portingRequest1;
    }

    //To accept outgoing requests
    public PortingRequest acceptOutgoingPortingRequest(PortingRequest portingRequest) {
        Optional<PortingRequest> portingRequest1 = requestRepository.findByPortingRequestId(portingRequest.getPortingRequestId());
        if(portingRequest1.isPresent()) {
            request2 = portingRequest1.get();
            requestRepository.deleteByPortingRequestId(request2.getPortingRequestId());
            request2.setFromApproval(1);
            Date d = new Date();
            request2.setAcceptedDateofPreviousInsurer(d);
            requestRepository.save(request2);
        }
        return request2;
    }

    //To accept incoming requests
    public PortingRequest acceptIncomingPortingRequest(PortingRequest portingRequest) {
        Optional<PortingRequest> portingRequest1 = requestRepository.findByPortingRequestId(portingRequest.getPortingRequestId());
        if(portingRequest1.isPresent()) {
            request2 = portingRequest1.get();
            requestRepository.deleteByPortingRequestId(request2.getPortingRequestId());
            request2.setToApproval(1);
            requestRepository.save(request2);
        }
        return request2;
    }

    //To delete a request
    public void deletePortingRequest(PortingRequest portingRequest) {
        requestRepository.deleteByInsuredNameAndCreateDate(portingRequest.getInsuredName(),portingRequest.getCreateDate());
    }

    //To reject incoming request
    public PortingRequest rejectIncomingPortingRequest(PortingRequest portingRequest) {
        Optional<PortingRequest> portingRequest1 = requestRepository.findByPortingRequestId(portingRequest.getPortingRequestId());
        if (portingRequest1.isPresent()) {
            request2 = portingRequest1.get();
            request2.setToApproval(2);
            requestRepository.save(request2);
        }
        return request2;
    }
}
