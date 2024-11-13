package com.kym.spring_boot_microservice_5_ProcesoReservaCita.Repository;

import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
}
