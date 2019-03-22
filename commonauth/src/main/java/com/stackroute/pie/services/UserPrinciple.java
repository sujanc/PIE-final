package com.stackroute.pie.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stackroute.pie.domain.CommonAuth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;

    private int insuredId;
    private String username;
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(
                         String username, String email, String password,
                         Collection<? extends GrantedAuthority> authorities) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrinciple build(CommonAuth commonAuth) {
        List<GrantedAuthority> authorities = commonAuth.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new UserPrinciple(

                commonAuth.getUsername(),
                commonAuth.getEmail(),
                commonAuth.getPassword(),
                authorities
        );
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
        return Objects.hash(username,insuredId,password);
    }
}
