package com.kym.spring_boot_microservice_1_GestionUsuario.repositorios;

import com.kym.spring_boot_microservice_1_GestionUsuario.persistencia.entidades.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);


    Optional<User> findByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.estado = 0 WHERE u.userId = :userId")
    void deleteUserEstado(Long userId);

    @Modifying
    @Query("UPDATE User u SET u.estado = 1 WHERE u.userId = :userId")
    void activeUserEstado(Long userId);
}
