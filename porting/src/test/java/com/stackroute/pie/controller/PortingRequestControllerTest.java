package com.stackroute.pie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.pie.domain.PortingRequest;
import com.stackroute.pie.exceptions.RequestNotFoundException;
import com.stackroute.pie.services.PortingRequestServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class PortingRequestControllerTest {

    @Autowired
    private MockMvc mockMVC;

    @Mock
    private PortingRequestServiceImpl portingRequestService;

    @InjectMocks
    private PortingRequestController portingRequestController;

    private PortingRequest portingRequest;
    private List<PortingRequest> portingRequestList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        mockMVC = MockMvcBuilders.standaloneSetup(portingRequestController).build();
        portingRequest = new PortingRequest(12,"sourabhi","anusha1","abcd",
                "apollo",25000,200000,"bonus",123,"manasa",
                "maxBupa",30000,250000,"plan change",
                2,false,0,0,"manasa@gmail.com");
//        PortingRequest portingRequest1 = new PortingRequest(22,"sourabhi","anusha1","bangalore","max bupa",25000,20000,"bonus",1223,"manasa","religare",30000,25000,"plan change",3,true,0,0);
        portingRequestList.add(portingRequest);
        portingRequestList.add(1,new PortingRequest(22,"sourabhi","anusha1",
                "bangalore","max bupa",25000,20000,"bonus",
                1223,"manasa","religare",30000,25000,
                "plan change",3,true,0,0,"dummy@gmail.com"));

    }
    @Test
    public void saveRequestSuccess() throws Exception {
        when(portingRequestService.postRequest(portingRequest)).thenReturn(portingRequest);
        mockMVC.perform(post("/api/v1/request")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonToString(portingRequest)))
                .andExpect(status().isOk());
        verify(portingRequestService, Mockito.times(1)).postRequest(Mockito.any(PortingRequest.class));
        verifyNoMoreInteractions(portingRequestService);


    }
    @Test
    public void getRequestsSuccess() throws Exception {
//            when(portingRequestService.getRequests(portingRequest.getInsuredName())).thenReturn(portingRequestList);
        mockMVC.perform(get("/api/v1/request/sourabhi")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(portingRequest)))
                .andExpect(status().isOk());
        verify(portingRequestService, Mockito.times(1)).getRequests(portingRequest.getInsuredName());
        verifyNoMoreInteractions(portingRequestService);
    }

    @Test
    public void updateSuccess() throws Exception {
        mockMVC.perform(put("/api/v1/request")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(portingRequest)))
                .andExpect(status().isOk());
        verify(portingRequestService, Mockito.times(1)).updateRequest(Mockito.any(PortingRequest.class));
        verifyNoMoreInteractions(portingRequestService);
    }

    @Test
    public void deleteRequestSuccess() throws Exception {
        mockMVC.perform(delete("/api/v1/request/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(portingRequest)))
                .andExpect(status().isOk());
        verify(portingRequestService, Mockito.times(1)).deleteRequest(portingRequest.getPortingRequestId());
        verifyNoMoreInteractions(portingRequestService);
    }

    private static String jsonToString(final Object obj) throws JsonProcessingException {
        String result;
        try {

            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "Json processing error";
        }
        return result;
    }

}