package com.stackroute.pie.message.request;

import com.stackroute.pie.domain.FamilyMembers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {

    @NotBlank
    @Size(min = 3, max = 50)

    private String fullName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    private Set<String> role;

    private int insuredId;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    private  String gender;

    private long age;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdDate = new Date();

    @NotBlank
    private String securityAnswer;

    private String existingDisease;

   private  List<FamilyMembers> familyMembers;

}

