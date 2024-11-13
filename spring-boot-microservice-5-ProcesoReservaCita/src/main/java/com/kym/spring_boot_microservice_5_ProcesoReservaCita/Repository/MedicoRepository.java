package com.kym.spring_boot_microservice_5_ProcesoReservaCita.Repository;

import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model.Medicos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medicos, Long> {
}
