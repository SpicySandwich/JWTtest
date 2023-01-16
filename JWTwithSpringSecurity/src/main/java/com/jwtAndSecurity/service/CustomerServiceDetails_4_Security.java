package com.jwtAndSecurity.service;

import com.jwtAndSecurity.model.Client_1_Security;
import com.jwtAndSecurity.model.CustomerUserDetails_2_Security;
import com.jwtAndSecurity.repository.UserSecurityRepository_3_Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerServiceDetails_4_Security implements UserDetailsService {

    @Autowired
    UserSecurityRepository_3_Security userSecurityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      Optional<Client_1_Security> user = userSecurityRepository.findByClientUsername(username);

      user.orElseThrow( () -> new UsernameNotFoundException("Not Found " + username));
        return user.map(CustomerUserDetails_2_Security::new).get();
    }
}
