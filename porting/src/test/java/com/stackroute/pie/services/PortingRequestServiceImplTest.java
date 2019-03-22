package com.stackroute.pie.services;

import com.stackroute.pie.domain.PortingRequest;
import com.stackroute.pie.exceptions.RequestNotFoundException;
import com.stackroute.pie.repository.PortingRequestRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class PortingRequestServiceImplTest {
    private Optional optional;
    @Mock
    private PortingRequestRepository portingRequestRepository;
    @InjectMocks
    private PortingRequestServiceImpl portingRequestService;
    private List<PortingRequest> portingRequestList = new ArrayList<>();
    private PortingRequest portingRequest;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        portingRequest = new PortingRequest(12, "sourabhi", "anusha1", "abcd", "apollo", 25000, 200000, "bonus", 123, "manasa", "maxBupa", 30000, 250000, "plan change", 2, false, 0, 0,"dumb@gmail.com");
        portingRequestList.add(portingRequest);
        portingRequestList.add(1, new PortingRequest(22, "sourabhi", "anusha1", "bangalore", "max bupa", 25000, 20000, "bonus", 1223, "manasa", "religare", 30000, 25000, "plan change", 3, true, 0, 0,"dumb@gmail.com"));

        optional = Optional.of(portingRequest);
    }

    @Test
    public void postRequest() {
        when(portingRequestRepository.save(portingRequest)).thenReturn(portingRequest);
        PortingRequest expectedOutput = portingRequestService.postRequest(portingRequest);
        assertEquals(portingRequest, expectedOutput);
    }

    @Test
    public void getRequests() throws RequestNotFoundException {

        when(portingRequestRepository.existsByInsuredName(portingRequest.getInsuredName())).thenReturn(true);
        when(portingRequestRepository.findByInsuredName(portingRequest.getInsuredName())).thenReturn(portingRequestList);
        List<PortingRequest> expectedOutput = portingRequestService.getRequests(portingRequest.getInsuredName());
        assertEquals(portingRequestList, expectedOutput);
    }

    @Test
    public void deleteRequest() throws RequestNotFoundException {
        System.out.println(portingRequest.getPortingRequestId());
        when(portingRequestRepository.findByPortingRequestId(portingRequest.getPortingRequestId())).thenReturn(optional);
        when(portingRequestRepository.existsByPortingRequestId(portingRequest.getPortingRequestId())).thenReturn(true);
        PortingRequest actual = portingRequestService.deleteRequest(portingRequest.getPortingRequestId());
        assertEquals(optional.get(), actual);
        verify(portingRequestRepository, times(1)).deleteByPortingRequestId(12);
    }

    @Test
    public void updateRequestSuccess() throws RequestNotFoundException {

        when(portingRequestRepository.existsByPortingRequestId(portingRequest.getPortingRequestId())).thenReturn(true);
        when(portingRequestRepository.save(portingRequest)).thenReturn(portingRequest);
        PortingRequest actual = portingRequestService.updateRequest(portingRequest);
        assertEquals(portingRequest,actual);
    }

}