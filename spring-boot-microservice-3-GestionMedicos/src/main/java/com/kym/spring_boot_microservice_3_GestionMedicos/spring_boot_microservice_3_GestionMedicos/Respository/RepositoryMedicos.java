package com.kym.spring_boot_microservice_3_GestionMedicos.spring_boot_microservice_3_GestionMedicos.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kym.spring_boot_microservice_3_GestionMedicos.spring_boot_microservice_3_GestionMedicos.Model.Medicos;


public interface RepositoryMedicos extends JpaRepository<Medicos, Long> {

    boolean existsByNombre(String nombre);

}
