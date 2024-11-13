package com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Model.Horarios;
import com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Service.ServiceHorarios;


@RestController
@RequestMapping("/api/GestionHorarios")
public class ControllerHorario {

    @Autowired
    ServiceHorarios serviceHorarios;

    @GetMapping("/listar")
    public List<Horarios> listarHorarios() {
        return serviceHorarios.listAllHorarios();
    }

    @GetMapping("/listar/{id}")
    public Horarios listarHorarios(@RequestParam("id") Long id) {
        return serviceHorarios.getHorarios(id);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearHorarios(@ModelAttribute Horarios horarios, @RequestParam("idMedico") Long idMedico,
            @RequestParam("idEstadoCita") Long idEstadoCita) {
        return serviceHorarios.createHorarios(horarios, idMedico, idEstadoCita);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarHorarios(@ModelAttribute Horarios horarios,@RequestParam("id") Long id) {
        return serviceHorarios.updateHorarios(horarios, id);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarHorarios(@PathVariable("id") Long id) {
        return serviceHorarios.deleteHorarios(id);
    }
}
