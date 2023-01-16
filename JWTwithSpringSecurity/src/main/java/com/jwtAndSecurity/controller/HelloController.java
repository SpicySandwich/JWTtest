package com.jwtAndSecurity.controller;

import com.jwtAndSecurity.model.JWTauthenticationRequest;
import com.jwtAndSecurity.model.JWTauthenticationResponse;
import com.jwtAndSecurity.service.CustomerServiceDetails_4_Security;
import com.jwtAndSecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerServiceDetails_4_Security customerServiceDetails_4_security;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/home")
    public String home(){
        return "Welcome home";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createTokenMethod(@RequestBody JWTauthenticationRequest jwTauthenticationRequest) throws Exception {
       try {
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwTauthenticationRequest.getUsername(),jwTauthenticationRequest.getPassword()));
       }catch ( BadCredentialsException e){
           throw new Exception("Incorrect password and username");
       }
        UserDetails userDetails =customerServiceDetails_4_security.loadUserByUsername(jwTauthenticationRequest.getUsername());
      String jwt =  jwtUtil.generateToken(userDetails);
      return ResponseEntity.ok(new JWTauthenticationResponse(jwt));
    }

}
