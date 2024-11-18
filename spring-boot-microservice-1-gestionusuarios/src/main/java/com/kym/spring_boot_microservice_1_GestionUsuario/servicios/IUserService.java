package com.kym.spring_boot_microservice_1_GestionUsuario.servicios;

import com.kym.spring_boot_microservice_1_GestionUsuario.persistencia.entidades.User;
import com.kym.spring_boot_microservice_1_GestionUsuario.servicios.implemen.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface IUserService {


    List<User> ListUsers();

    ApiResponse<?> SaveUser(User usuario);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    ApiResponse<User>  modificarUsuario(Long id, User usuarioActualizado);

    ApiResponse<User> deleteUser (@PathVariable("idUser") Long idUser);

    ApiResponse<User> activeUser(@PathVariable("idUser") Long idUser);
}
