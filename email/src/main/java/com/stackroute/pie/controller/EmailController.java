package com.stackroute.pie.controller;

import com.stackroute.pie.domain.Email;
import com.stackroute.pie.services.EmailService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/")
@CrossOrigin("*")
@Data
public class EmailController {
    @Autowired
    public EmailService emailService;

    @PostMapping("email/")
    public ResponseEntity sendEmail(@RequestBody Email email) {
        return new ResponseEntity(emailService.sendSimpleMessage(email.getTo(), email.getSubject(), email.getBody()), HttpStatus.OK);

    }
}
