package com.kym.springbootmicroservice0apigateway.controller;

import com.kym.springbootmicroservice0apigateway.model.User;
import com.kym.springbootmicroservice0apigateway.service.AuthenticationService;
import com.kym.springbootmicroservice0apigateway.service.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("sign-up")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();

        if (userService.findByUsername(user.getUsername()).isPresent()) {
            response.put("message", "El nombre de usuario ya está en uso.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        if (userService.findByEmail(user.getEmail()).isPresent()) {
            response.put("message", "El correo electrónico ya está en uso.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        userService.saveUser(user);
        response.put("message", "Usuario registrado exitosamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody User user) {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }
}
