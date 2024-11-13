package com.kym.springbootmicroservice0apigateway.service;

import com.kym.springbootmicroservice0apigateway.model.Role;
import com.kym.springbootmicroservice0apigateway.model.User;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    void changeRole(Role role, String usarname);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User findByUsernameReturnToken(String username);
}
