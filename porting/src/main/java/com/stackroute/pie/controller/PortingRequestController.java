package com.stackroute.pie.controller;

import com.stackroute.pie.domain.PortingRequest;
import com.stackroute.pie.exceptions.RequestNotFoundException;
import com.stackroute.pie.repository.PortingRequestRepository;
import com.stackroute.pie.services.PortingRequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin("*")
public class PortingRequestController {

    @Autowired
    private KafkaTemplate<String, PortingRequest> kafkaTemplate;

    PortingRequestServiceImpl requestService;

    PortingRequestRepository portingRequestRepository;

    private String insurerNotFoundMessage = "Insurer not found";
    @Autowired
    public PortingRequestController(PortingRequestServiceImpl requestService, PortingRequestRepository portingRequestRepository) {
        this.requestService = requestService;
        this.portingRequestRepository = portingRequestRepository;
    }

    //Method to store porting request details
    @PostMapping("request")
    public ResponseEntity postRequest (@RequestBody PortingRequest request) {
        ResponseEntity responseEntity;
        PortingRequest request1 = requestService.postRequest(request);

        responseEntity = new ResponseEntity<PortingRequest>(request1, HttpStatus.OK);
        return responseEntity;
    }

    //Method to update the request details
    @PutMapping("request")
    public ResponseEntity updateRequest (@RequestBody PortingRequest request){
        ResponseEntity responseEntity;

        try {
            PortingRequest request1 = requestService.updateRequest(request);
            responseEntity =  new ResponseEntity(request1, HttpStatus.OK);
        }
        catch (RequestNotFoundException ex) {
            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;

    }

    //Method to delete porting request
    @DeleteMapping("request/{requestId}")
    public ResponseEntity deleteRequest (@PathVariable("requestId") int requestId){
        ResponseEntity responseEntity;

        try {

            PortingRequest request1 = requestService.deleteRequest(requestId);
            responseEntity =  new ResponseEntity(request1, HttpStatus.OK);

        }
        catch (RequestNotFoundException ex) {
            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;

    }

    //Method to display requests
    @GetMapping("request/{username}")
    public ResponseEntity requestLists(@PathVariable("username") String insuredName) {
        ResponseEntity responseEntity;
        try {
            List<PortingRequest> allRequests = requestService.getRequests(insuredName);
            return new ResponseEntity(allRequests, HttpStatus.OK);
        }
        catch (RequestNotFoundException ex) {
            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //Method to display incoming porting requests
    @GetMapping("/incomingportingrequest/{newInsurerName}")
    public ResponseEntity incomingPortingRequest( @PathVariable("newInsurerName") String newInsurerName) {
        if (!portingRequestRepository.existsByNewInsurerName(newInsurerName)) {
            return new ResponseEntity(insurerNotFoundMessage,
                    HttpStatus.NOT_FOUND);
        }
        List<PortingRequest> incomingPortingRequests = requestService.getIncomingPortingRequest(newInsurerName);
        List<PortingRequest> incomingPortingRequests1 = new ArrayList<>();
        for(int i = 0;i < incomingPortingRequests.size(); i++) {
            if(incomingPortingRequests.get(i).getFromApproval() == 1 && incomingPortingRequests.get(i).getToApproval() == 0) {
                incomingPortingRequests1.add(incomingPortingRequests.get(i));
            }
        }
        return new ResponseEntity(incomingPortingRequests1, HttpStatus.OK);
    }

    //Method to display outgoing porting requests
    @GetMapping("/outgoingportingrequest/{insurerName}")
    public ResponseEntity outgoingPortingRequest(@PathVariable("insurerName") String insurerName) {
        if (!portingRequestRepository.existsByInsurerName(insurerName)) {
            return new ResponseEntity(insurerNotFoundMessage,
                    HttpStatus.NOT_FOUND);
        }
        List<PortingRequest> outgoingPortingRequests = requestService.getOutgoingPortingRequest(insurerName);
        List<PortingRequest> outgoingPortingRequests1 = new ArrayList<>();
        for(int i =0 ;i < outgoingPortingRequests.size(); i++) {
            if(outgoingPortingRequests.get(i).getFromApproval() != 1) {
                outgoingPortingRequests1.add(outgoingPortingRequests.get(i));
            }
        }
        return new ResponseEntity(outgoingPortingRequests1, HttpStatus.OK);
    }

    //Method to accept outgoing requests
    @PutMapping("/acceptoutgoingportingrequest")
    public ResponseEntity acceptOutgoingPortingRequest(@RequestBody PortingRequest portingRequest) {
        if (!portingRequestRepository.existsByInsurerName(portingRequest.getInsurerName())) {
            return new ResponseEntity(insurerNotFoundMessage,
                    HttpStatus.NOT_FOUND);
        }
        PortingRequest acceptOutgoingPortingRequest = requestService.acceptOutgoingPortingRequest(portingRequest);
        return new ResponseEntity(acceptOutgoingPortingRequest, HttpStatus.OK);
    }

    //Method to accept outgoing requests
    @PutMapping("/acceptincomingportingrequest")
    public ResponseEntity acceptIncomingPortingRequest(@RequestBody PortingRequest portingRequest) {
        if (!portingRequestRepository.existsByNewInsurerName(portingRequest.getNewInsurerName())) {
            return new ResponseEntity(insurerNotFoundMessage,
                    HttpStatus.NOT_FOUND);
        }
        PortingRequest acceptIncomingPortingRequest = requestService.acceptIncomingPortingRequest(portingRequest);
        kafkaTemplate.send("incomingporting", acceptIncomingPortingRequest);
        return new ResponseEntity(acceptIncomingPortingRequest, HttpStatus.OK);
    }

    //Method to reject incoming requests
    @PutMapping("/rejectincomingportingrequest")
    public ResponseEntity rejectIncomingPortingRequest(@RequestBody PortingRequest portingRequest) {
        if (!portingRequestRepository.existsByNewInsurerName(portingRequest.getNewInsurerName())) {
            return new ResponseEntity(insurerNotFoundMessage,
                    HttpStatus.NOT_FOUND);
        }
        PortingRequest rejectIncomingPortingRequest = requestService.rejectIncomingPortingRequest(portingRequest);
        return new ResponseEntity(rejectIncomingPortingRequest, HttpStatus.OK);
    }

    //Get portingrequest based on porting request ID
    @GetMapping("outgoingportingrequest/portingRequestId={portingRequestId}")
    public ResponseEntity getPortingRequestByPortingRequestId(@PathVariable (value = "portingRequestId", required = true) int portingRequestId) {
        return new ResponseEntity(requestService.getPortingRequestByPortingRequestId(portingRequestId), HttpStatus.OK);
    }

    //Get the history of porting request for Insurer
    @GetMapping("requests/{insurerName}")
    public ResponseEntity getPortingRequestsByInsurerName(@PathVariable("insurerName") String insurerName) {
        if (!portingRequestRepository.existsByNewInsurerName(insurerName)) {
            return new ResponseEntity(insurerNotFoundMessage,
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(requestService.getRequestsforInsurer(insurerName), HttpStatus.OK);
    }

}