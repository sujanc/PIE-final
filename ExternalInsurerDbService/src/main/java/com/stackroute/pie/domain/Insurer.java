package com.stackroute.pie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Document(collection="insurersname")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Insurer {

    private Long insurerId;

    @NotBlank
    @Size(min=3, max = 50)
    private String insurerName;

    @NotBlank
    @UniqueElements
    @Size(min=3, max = 50)
    private String insurerLicense; //username

    @UniqueElements
    @NotBlank
    @Size(max = 50)
    @Email
    private String insurerEmail;

    //    @Transactional
    @NotBlank
    @Size(min=6, max = 100)
    private String password;

    @NotBlank
    @Size(min=6, max = 100)
    private String insurerAddress;

    @NotBlank
    private String securityQuestion;



    @NotBlank
    @Size(min=6, max = 40)
    private String securityAnswer;



    //document inside the doucmnet
    private List<Policy> policies;

    private Set<Role> roles = new HashSet<>();



}
