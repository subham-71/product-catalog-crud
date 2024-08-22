package com.training.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.webapp.entity.User;
import com.training.webapp.service.UserService;
import com.training.webapp.util.JwtUtil;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public String createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        final UserDetails userDetails = userService
                .findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new Exception("User not found"));

        return jwtUtil.generateToken(userDetails);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        // By default, register users as "USER". Admin registration should be handled separately.
        return userService.registerUser(user.getUsername(), user.getPassword(), "ROLE_USER");
    }
}
