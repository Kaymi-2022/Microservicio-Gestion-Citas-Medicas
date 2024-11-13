package com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Model.Horarios;

public interface ServiceHorarios {

    public List<Horarios> listAllHorarios();

    public Horarios getHorarios(Long id);

    public ResponseEntity<?> createHorarios(Horarios horarios, Long idMedico, Long idEstadoCita);

    public ResponseEntity<?> updateHorarios(@ModelAttribute Horarios horarios, @RequestParam("id") Long id);

    public ResponseEntity<?> deleteHorarios(Long id);
}
