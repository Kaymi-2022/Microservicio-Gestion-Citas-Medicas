package com.kym.spring_boot_microservice_5_ProcesoReservaCita.Repository;

import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
}
