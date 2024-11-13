package com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Service.Implements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Model.EstadoCita;
import com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Model.Horarios;
import com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Model.Medicos;
import com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Repository.EstadoCitaRepository;
import com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Repository.HorariosRepository;
import com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Repository.MedicosRepository;
import com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Service.ServiceHorarios;

@Service
public class ServiceHorariosImpl implements ServiceHorarios {

    @Autowired
    HorariosRepository horariosRepository;

    @Autowired
    MedicosRepository medicosRepository;

    @Autowired
    EstadoCitaRepository estadoCitaRepository;

    @Override
    public List<Horarios> listAllHorarios() {
        return horariosRepository.findAll();
    }

    @Override
    public Horarios getHorarios(Long id) {
        return horariosRepository.findById(id).get();
    }

    @Override
    public ResponseEntity<?> createHorarios(Horarios horarios, Long idMedico, Long idEstadoCita) {
  
        if (horariosRepository.existsByDiaAndTime(horarios.getDia(), horarios.getTime())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>("Horarios no Creado", "Ya existe un horario con ese día y hora"));
        }
    
        try {
     
            Medicos medicos = medicosRepository.findByidMedico(idMedico);
            EstadoCita estadoCita = estadoCitaRepository.findByIdestadoCita(idEstadoCita);
    
            if (medicos == null || estadoCita == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>("Horarios no Creado", "Médico o estado de cita no encontrado"));
            }
    
            horarios.setMedicos(medicos);
            horarios.setIdestadoCita(estadoCita);
            Horarios nuevoHorario = horariosRepository.save(horarios);
    
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>("Horarios creado exitosamente", nuevoHorario));
    
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Error del Proceso", "Error de base de datos: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Error del Proceso", "Error inesperado: " + e.getMessage()));
        }
    }
    
    @Override
    public ResponseEntity<?> updateHorarios(@ModelAttribute Horarios horarios, @RequestParam("id") Long id) {
        try {
            // Verifica si ya existe un horario con el mismo día y hora
            if (horariosRepository.existsByDiaAndTime(horarios.getDia(), horarios.getTime())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new ApiResponse<>("Horarios no Actualizado", "Ya existe un horario con ese día y hora"));
            }

            Optional<Horarios> optionalHorarios = horariosRepository.findById(id);
            if (!optionalHorarios.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>("Horarios no Actualizado", "Horario no encontrado"));
            }

            Long idMedico = horarios.getMedicos() != null ? horarios.getMedicos().getIdMedico() : null;
            if (idMedico == null || !medicosRepository.existsByidMedico(idMedico)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>("Horarios no Actualizado", "Médico no encontrado o inválido"));
            }

            Long idEstadoCita = horarios.getIdestadoCita() != null ? horarios.getIdestadoCita().getIdestadoCita() : null;
            if (idEstadoCita == null || !estadoCitaRepository.existsByIdestadoCita(idEstadoCita)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>("Horarios no Actualizado", "Estado de cita no encontrado o inválido"));
            }

            EstadoCita estadoCita = estadoCitaRepository.findByIdestadoCita(idEstadoCita);
            Medicos medicos = medicosRepository.findByidMedico(idMedico);

            Horarios horariosExistente = optionalHorarios.get();
            horariosExistente.setDia(horarios.getDia());
            horariosExistente.setTime(horarios.getTime());
            horariosExistente.setIdestadoCita(estadoCita);
            horariosExistente.setMedicos(medicos);

            // Guarda y retorna la respuesta
            Horarios actualizado = horariosRepository.save(horariosExistente);
            return ResponseEntity.ok(new ApiResponse<>("Horarios actualizado exitosamente", actualizado));

        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Error del Proceso",
                            "Error al acceder a la base de datos: " + e.getMessage()));
        } catch (Exception e) {
            // Manejo genérico de cualquier otra excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Error del Proceso", "Ocurrió un error inesperado: " + e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> deleteHorarios(@RequestParam("id") Long id) {
        
        if (!horariosRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Error de eliminación", "Horario no encontrado"));
        } 
        try {
            horariosRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<>("Horario eliminado exitosamente", "Horario id: " + id + " eliminado"));
        } catch (DataAccessException e) {
            // Handle specific database-related errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Error de eliminación", "Error al acceder a la base de datos: " + e.getMessage()));
        } catch (Exception e) {
            // Handle unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Error inesperado", "Ocurrió un error inesperado: " + e.getMessage()));
        }
    }
    
    
}
