package com.stackroute.pie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Document(collection="insurersname")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insurer {
    @Id
    private Long insurerId;

    @NotBlank
    @Size(min=3, max = 50)
    private String insurerName;

    //Insurerlicense would act like Unique UserName which we would use for login
    @NotBlank
    @UniqueElements
    @Size(min=3, max = 50)
    private String insurerLicense;

    @UniqueElements
    @NotBlank
    @Size(max = 50)
    @Email
    private String insurerEmail;

    @NotBlank
    @Size(min=6, max = 100)
    private String password;

    @NotBlank
    @Size(min=6, max = 100)
    private String insurerAddress;

    @NotBlank
    @Size(min=6, max = 40)
    private String securityQuestion;

    @NotBlank
    @Size(min=6, max = 40)
    private String securityAnswer;

    private Set<Role> roles = new HashSet<>();

    public Insurer(String insurerName, String insurerLicense, String insurerEmail, String password,String insurerAddress,String securityQuestion,String securityAnswer) {
        this.insurerName = insurerName;
        this.insurerLicense = insurerLicense;
        this.insurerEmail = insurerEmail;
        this.password = password;
        this.insurerAddress = insurerAddress;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

}






