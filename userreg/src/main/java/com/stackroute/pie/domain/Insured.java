package com.stackroute.pie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Document(collection="usersname")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insured {

    private int insuredId;

    @NotBlank
    @Size(min = 3, max = 50)
    private String fullName;

    @NotBlank
    @UniqueElements
    @Size(min = 3, max = 50)
    private String username;

    @UniqueElements
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    private long age;


    @NotBlank
    private String gender;

    private  String existingDisease;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdDate = new Date();


    private Set<Role> roles = new HashSet<>();

    private  List<FamilyMembers> familyMembers;


    public Insured(int insuredId) {
        this.insuredId = insuredId;
    }


    @NotBlank
    private String securityAnswer;



    public Insured(String existingDisease, String username, String email, String password, String gender, Date createdDate, String securityAnswer, long age,List<FamilyMembers> familyMembers){
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.createdDate = createdDate;
        this.securityAnswer = securityAnswer;
        this.age = age;
        this.existingDisease = existingDisease;
        this.familyMembers = familyMembers;
    }

    public Insured(int insuredId, @NotBlank @Size(min = 3, max = 50) String fullName, @NotBlank @UniqueElements @Size(min = 3, max = 50) String username, @UniqueElements @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 6, max = 100) String password, long age, @NotBlank String gender, @NotBlank String securityAnswer) {
        this.insuredId = insuredId;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.securityAnswer = securityAnswer;
    }
}






