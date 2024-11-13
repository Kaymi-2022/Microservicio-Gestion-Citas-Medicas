package com.kym.spring_boot_microservice_5_ProcesoReservaCita.Repository;

import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RepositoryCitas extends JpaRepository<Citas, Long> {



    boolean existsByUsuarioAndHorario(User usuario, Horarios horario);

    boolean existsByUsuario(Long id);

    Optional<Citas> findByUsuario(Long id);

    boolean existsByGenero(String genero);

    Optional<Citas> findByGenero(String genero);
}
