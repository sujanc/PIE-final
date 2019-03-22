package com.stackroute.pie.message.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
    @NotBlank
    @Size(min = 3, max = 50)

    private String insurerName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String insurerLicense;

    @NotBlank
    @Size(max = 60)
    @Email
    private String insurerEmail;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @Size(min = 6, max = 40)
    private String securityQuestion;

    @Size(min = 6, max = 40)
    private String securityAnswer;

    @Size(min = 6, max = 50)
    private String insurerAddress;
}

