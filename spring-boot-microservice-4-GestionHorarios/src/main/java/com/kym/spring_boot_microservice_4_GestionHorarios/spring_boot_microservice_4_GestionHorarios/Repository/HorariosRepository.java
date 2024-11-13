package com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Repository;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Model.Horarios;


public interface HorariosRepository extends JpaRepository<Horarios, Long> {

    boolean existsByDiaAndTime(LocalDate dia, LocalTime time);

}
