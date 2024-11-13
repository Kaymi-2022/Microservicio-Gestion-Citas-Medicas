package com.kym.spring_boot_microservice_2_GestionEspecialidades.spring_boot_microservice_2_GestionEspecialidades.Service.Implements;

import com.kym.spring_boot_microservice_2_GestionEspecialidades.spring_boot_microservice_2_GestionEspecialidades.Model.Especialidad;
import com.kym.spring_boot_microservice_2_GestionEspecialidades.spring_boot_microservice_2_GestionEspecialidades.Repository.EspecialidadRepository;
import com.kym.spring_boot_microservice_2_GestionEspecialidades.spring_boot_microservice_2_GestionEspecialidades.Service.IEspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService {

    @Autowired
    EspecialidadRepository especialidadRepository;

    @Override
    public List<Especialidad> ListEspecialidades()
    {
        return especialidadRepository.findAll();
    }

    @Override
    public ResponseEntity<?> CreateEspecialidad(Especialidad especialidad)
    {
        if (especialidadRepository.existsByNombre(especialidad.getNombre()))
        {
            ApiResponse<Especialidad> response = new ApiResponse<>("Especialidad ya existe", null);
            return ResponseEntity.status(400).body(response);
        }
        Especialidad savedEspecialidad = especialidadRepository.save(especialidad);
        ApiResponse<Especialidad> response = new ApiResponse<>("Especialidad guardada", savedEspecialidad);
        return ResponseEntity.ok(response);
    }

    @Override
    public Especialidad UpdateEspecialidad(Long id, Especialidad especialidad)
    {
        Especialidad especialidadToUpdate = especialidadRepository.findById(id).get();
        especialidadToUpdate.setNombre(especialidad.getNombre());
        especialidadToUpdate.setFoto(especialidad.getFoto());
        return especialidadRepository.save(especialidadToUpdate);
    }
    @Override
    public Especialidad DeleteEspecialidad(Long id)
    {
        Especialidad especialidadToUpdate = especialidadRepository.findById(id).get();
        especialidadToUpdate.setEstado(0);
        return especialidadRepository.save(especialidadToUpdate);
    }

    @Override
    public Especialidad ActivateEspecialidad(Long id)
    {
        Especialidad especialidadToUpdate= especialidadRepository.findById(id).get();
        especialidadToUpdate.setEstado(1);
        return especialidadRepository.save(especialidadToUpdate);
    }

    @Override
    public Especialidad findByIdEspecialidad(Long id)
    {
        return especialidadRepository.findById(id).get();
    }
}
