package com.stackroute.pie.repository;

import com.stackroute.pie.domain.CommonAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommonAuthRepository extends JpaRepository<CommonAuth,Integer> {
    Optional<CommonAuth> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
