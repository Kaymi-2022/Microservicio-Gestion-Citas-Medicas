package com.kym.springbootmicroservice0apigateway.repository;

import com.kym.springbootmicroservice0apigateway.model.Role;
import com.kym.springbootmicroservice0apigateway.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //findBy + nombreCampo
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
    @Modifying
    @Query("UPDATE User u SET u.role = :role WHERE u.username = :username")
    void updateUserRole(@Param("username") String username, @Param("role") Role role);
}
