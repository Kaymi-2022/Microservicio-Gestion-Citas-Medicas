package com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Model.Medicos;

public interface MedicosRepository extends JpaRepository<Medicos, Long> {

    Medicos findByidMedico(Long id);

    boolean existsByidMedico(Long id);

}
