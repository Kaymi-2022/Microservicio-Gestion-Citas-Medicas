package com.kym.springbootmicroservice0apigateway.service;

import com.kym.springbootmicroservice0apigateway.model.User;
import com.kym.springbootmicroservice0apigateway.repository.UserRepository;
import com.kym.springbootmicroservice0apigateway.security.UserPrincipal;
import com.kym.springbootmicroservice0apigateway.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements  AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User signInAndReturnJWT(User signInRequest)
    {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);

        User sigInUser = userPrincipal.getUser();
        sigInUser.setToken(jwt);

        return sigInUser;
    }

}
