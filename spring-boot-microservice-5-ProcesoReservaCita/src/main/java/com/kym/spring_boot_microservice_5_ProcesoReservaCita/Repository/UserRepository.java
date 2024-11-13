package com.kym.spring_boot_microservice_5_ProcesoReservaCita.Repository;

import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
