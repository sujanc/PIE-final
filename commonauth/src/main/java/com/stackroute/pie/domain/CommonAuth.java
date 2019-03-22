package com.stackroute.pie.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CommonAuthentication")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class   CommonAuth {

@Id
 @GeneratedValue(strategy=GenerationType.AUTO)

    private int id;
    private  String name;
    private String username;
    private String password;
    private String email;
   private int count;


   @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Set<Role> roles = new HashSet<>();



}
