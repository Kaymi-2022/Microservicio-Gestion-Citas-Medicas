package com.kym.spring_boot_microservice_5_ProcesoReservaCita.Repository;

import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model.Horarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioRepository extends JpaRepository<Horarios, Long> {
}
