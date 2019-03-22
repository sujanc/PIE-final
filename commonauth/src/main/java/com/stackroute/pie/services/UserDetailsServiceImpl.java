package com.stackroute.pie.services;

import com.stackroute.pie.domain.CommonAuth;

import com.stackroute.pie.repository.CommonAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    CommonAuthRepository commonAuthRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
      CommonAuth commonAuth = commonAuthRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Insured Not Found with -> username or email : " + username));

        return UserPrinciple.build(commonAuth);
    }



    public Integer getCount(String insurerName) {
        Optional<CommonAuth> commonAuthOptional = commonAuthRepository.findByUsername(insurerName);
        if(commonAuthOptional.isPresent()) {
            return commonAuthOptional.get().getCount();
        }
        return -1;
    }


    public Integer updateCount(String insurerName) {
        Optional<CommonAuth> commonAuthOptional = commonAuthRepository.findByUsername(insurerName);
        if(commonAuthOptional.isPresent()) {
            CommonAuth commonAuth = commonAuthOptional.get();
            int c1= commonAuth.getCount()+1;
            commonAuth.setCount(c1);
            commonAuthRepository.save(commonAuth);
            return (commonAuth.getCount());
        }
        return -1;
    }
}
