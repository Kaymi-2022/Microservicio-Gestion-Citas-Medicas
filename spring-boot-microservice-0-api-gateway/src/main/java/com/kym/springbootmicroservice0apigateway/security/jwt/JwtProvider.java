package com.kym.springbootmicroservice0apigateway.security.jwt;

import com.kym.springbootmicroservice0apigateway.model.User;
import com.kym.springbootmicroservice0apigateway.security.UserPrincipal;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface JwtProvider {
    String generateToken(UserPrincipal auth);


    String generateToken(User user);

    Authentication getAuthentication(HttpServletRequest request);

    boolean isTokenValid(HttpServletRequest request);
}
