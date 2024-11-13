package com.kym.springbootmicroservice0apigateway.security;

import com.kym.springbootmicroservice0apigateway.model.User;
import com.kym.springbootmicroservice0apigateway.service.UserService;
import com.kym.springbootmicroservice0apigateway.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("El usuario no fue encontrado:"+username));


        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));


        return UserPrincipal.builder()
                .user(user)
                .id(user.getUserId())
                .username(username)
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
