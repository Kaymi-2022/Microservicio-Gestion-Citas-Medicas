package com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Model.EstadoCita;


public interface EstadoCitaRepository extends JpaRepository<EstadoCita, Long> {

    EstadoCita findByIdestadoCita(Long idestadoCita);

    boolean existsByIdestadoCita(Long idestadoCita);
}
