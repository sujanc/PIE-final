package com.stackroute.pie.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stackroute.pie.domain.Insured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
public class UserPrinciple implements UserDetails {

    private static final long serialVersionUID = 1L;

    private int insuredId;

    private String fullName;

    private String username;

    private String email;

    private  String gender;

    @JsonIgnore
    private String password;




    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(int insuredId, String fullName,
                         String username, String email, String password,String gender,
                         Collection<? extends GrantedAuthority> authorities) {
        this.insuredId = insuredId;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender=gender;
        this.authorities = authorities;
    }

    public static UserPrinciple build(Insured insured) {
        List<GrantedAuthority> authorities = insured.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrinciple(
                insured.getInsuredId(),
                insured.getFullName(),
                insured.getUsername(),
                insured.getEmail(),
                insured.getPassword(),
                insured.getGender(),
                authorities
        );
    }
    public int getInsuredId() {
        return insuredId;
    }
    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(insuredId, user.insuredId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(insuredId,password,username);
    }
}