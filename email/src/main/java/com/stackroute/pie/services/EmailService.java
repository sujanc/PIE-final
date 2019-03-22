package com.stackroute.pie.services;

import com.stackroute.pie.domain.Email;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    Email sendSimpleMessage(String to, String subject, String body);

}
