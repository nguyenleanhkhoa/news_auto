package com.triviet.project.viet24.controller;

import com.triviet.project.viet24.entity.CustomUserDetail;
import com.triviet.project.viet24.entity.User;
import com.triviet.project.viet24.jwt.JwtTokenProvider;
import com.triviet.project.viet24.payload.LoginRequest;
import com.triviet.project.viet24.payload.LoginResponse;
import com.triviet.project.viet24.repository.UserRepository;
import com.triviet.project.viet24.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class RestLoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signin")
    public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {

        String jwt = "";
        User userDetails = userRepository.loadUser(loginRequest.getUsername(), loginRequest.getPassword());
        if(userDetails != null) {
            jwt = tokenProvider.generateToken(userDetails);
        }
        // Trả về jwt cho người dùng.
        return new LoginResponse(jwt);
    }

}
