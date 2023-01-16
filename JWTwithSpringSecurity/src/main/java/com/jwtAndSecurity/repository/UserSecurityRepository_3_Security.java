package com.jwtAndSecurity.repository;

import com.jwtAndSecurity.model.Client_1_Security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSecurityRepository_3_Security extends JpaRepository<Client_1_Security,Integer> {

    Optional<Client_1_Security> findByClientUsername(String username);


}
