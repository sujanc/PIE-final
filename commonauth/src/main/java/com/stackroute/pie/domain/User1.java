package com.stackroute.pie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User1 {

    private Long insuredId;
    private String fullName;
    private String username;
    private String email;
    private String password;
    private  String gender;
    private Date createdDate = new Date();
    private Set<Role> roles = new HashSet<>();
    private Set<Policy> policies =new HashSet<>();
    private String securityAnswer;

    public User1(Long insuredId, String fullName, String username, String email, String password, String gender, Date createdDate, String securityAnswer, Set<Role> roles, Set<Policy> policies) {
        this.insuredId = insuredId;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.createdDate = createdDate;
        this.securityAnswer = securityAnswer;
        this.roles = roles;
        this.policies = policies;
    }

    @Override
    public String toString() {
        return "User1{" +
                "insuredId=" + insuredId +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", createdDate=" + createdDate +
                ", roles=" + roles +
                ", securityAnswer='" + securityAnswer + '\'' +
                '}';
    }
}
