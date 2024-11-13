package com.kym.springbootmicroservice0apigateway.service;

import com.kym.springbootmicroservice0apigateway.model.User;

public interface AuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}
