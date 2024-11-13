package com.kym.spring_boot_microservice_2_GestionEspecialidades.spring_boot_microservice_2_GestionEspecialidades.Service;

import org.springframework.http.ResponseEntity;

import com.kym.spring_boot_microservice_2_GestionEspecialidades.spring_boot_microservice_2_GestionEspecialidades.Model.Especialidad;

import java.util.List;

public interface IEspecialidadService {
    List<Especialidad> ListEspecialidades();

    ResponseEntity<?> CreateEspecialidad(Especialidad especialidad);

    Especialidad UpdateEspecialidad(Long id, Especialidad especialidad);

    Especialidad DeleteEspecialidad(Long id);

    Especialidad ActivateEspecialidad(Long id);

    Especialidad findByIdEspecialidad(Long id);
}
