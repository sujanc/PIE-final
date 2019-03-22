package com.stackroute.pie.services;

import com.stackroute.pie.domain.FamilyMembers;
import com.stackroute.pie.domain.Insured;
import com.stackroute.pie.repository.UserRepository;
import com.stackroute.pie.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)  {
        Insured insured = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Insured Not Found with -> username or email : " + username));

        return UserPrinciple.build(insured);
    }

    //to post the dummy requests to the database
    public Insured postRequest(Insured insured){
        return userRepository.save(insured);
    }



    public Insured getProfile(String username) throws UserNotFoundException{
        if(userRepository.existsByUsername(username)){
            Optional<Insured> insured= userRepository.findByUsername(username);
            if(insured.isPresent())
            {
                return insured.get();
            }
            else {
                throw new UserNotFoundException();
            }
        }
        else
            throw new UserNotFoundException();
    }

    public Insured updateProfile(Insured insured) throws UserNotFoundException {
        Insured insured1 = null;
        if (userRepository.existsByUsername(insured.getUsername())) {
            Optional<Insured> insured2 = userRepository.findByUsername(insured.getUsername());
            if(insured2.isPresent()) {
                insured1 = insured2.get();
                userRepository.deleteByUsername(insured.getUsername());
                insured1.setEmail(insured.getEmail());
                insured1.setFullName(insured.getFullName());
                insured1.setAge(insured.getAge());
                insured1.setGender(insured.getGender());
                insured1.setPassword(insured.getPassword());
                insured1.setExistingDisease(insured.getExistingDisease());
                userRepository.save(insured1);
            }
            return insured1;
        }
        else
            throw new UserNotFoundException();
    }
    public Insured addFamilyMembers(FamilyMembers familyMembers) throws UserNotFoundException{
        String name = familyMembers.getUsername();
        Optional<Insured> insured2 = userRepository.findByUsername(name);
        if(insured2.isPresent())
        {
            Insured insured1 = insured2.get();
            List<FamilyMembers> familyMembersList = new ArrayList<>();
            familyMembersList.add(familyMembers);
            insured1.setFamilyMembers(familyMembersList);
            userRepository.deleteByUsername(name);
            return userRepository.save(insured1);
        }
        else {
            throw new UserNotFoundException();
        }

    }

    public String getUserEmail(String username) {
        Optional<Insured> insured =  userRepository.findByUsername(username);
        if(insured.isPresent()) {
            return insured.get().getEmail();
        }
        return "";
    }
}
