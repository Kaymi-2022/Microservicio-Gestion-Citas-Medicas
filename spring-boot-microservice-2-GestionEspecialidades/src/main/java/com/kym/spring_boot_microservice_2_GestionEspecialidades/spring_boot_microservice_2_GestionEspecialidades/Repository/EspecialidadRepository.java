package com.kym.spring_boot_microservice_2_GestionEspecialidades.spring_boot_microservice_2_GestionEspecialidades.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.kym.spring_boot_microservice_2_GestionEspecialidades.spring_boot_microservice_2_GestionEspecialidades.Model.Especialidad;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

    boolean existsByNombre(String nombre);
}
