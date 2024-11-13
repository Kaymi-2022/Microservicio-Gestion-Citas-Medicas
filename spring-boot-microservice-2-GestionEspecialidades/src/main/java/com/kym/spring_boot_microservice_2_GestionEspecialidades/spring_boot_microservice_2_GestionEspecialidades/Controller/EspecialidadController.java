package com.kym.spring_boot_microservice_2_GestionEspecialidades.spring_boot_microservice_2_GestionEspecialidades.Controller;

import com.kym.spring_boot_microservice_2_GestionEspecialidades.spring_boot_microservice_2_GestionEspecialidades.Model.Especialidad;
import com.kym.spring_boot_microservice_2_GestionEspecialidades.spring_boot_microservice_2_GestionEspecialidades.Service.IEspecialidadService;
import com.kym.spring_boot_microservice_2_GestionEspecialidades.spring_boot_microservice_2_GestionEspecialidades.Service.Implements.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/GestionEspecialidades")
public class EspecialidadController {

    @Autowired
    private IEspecialidadService iEspecialidadService;

    ApiResponse<Especialidad> response;

    @GetMapping("listar")
    public ResponseEntity<?> getEspecialidades() {
        try {
            List<Especialidad> listarEspecialidades = iEspecialidadService.ListEspecialidades();
            return ResponseEntity.ok(listarEspecialidades);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar las especialidades: " + e.getMessage());
        }
    }

    @PostMapping("guardar")
    public ResponseEntity<?> createEspecialidad(@ModelAttribute Especialidad especialidad, @RequestParam("file") MultipartFile imagen){
        try {
            if (!imagen.isEmpty()){
                //Path directorioImagenes= Paths.get("src//main//resources//static//imagenes");
                //String rutaAbsoluta= directorioImagenes.toFile().getAbsolutePath();
                String rutaAbsoluta= "C://Repositorio//imagenes";
                byte[] bytesImg= imagen.getBytes();
                Path rutaCompleta= Paths.get(rutaAbsoluta+"//"+imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                especialidad.setFoto(imagen.getOriginalFilename());
            }

            return iEspecialidadService.CreateEspecialidad(especialidad);
        } catch (Exception e) {
            response = new ApiResponse<>("Error guardando la especialidad: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<?> updateEspecialidad(@PathVariable("id") Long id, @ModelAttribute Especialidad especialidad,
                                                @RequestParam("file") MultipartFile imagen){
        try {
            String rutaAbsoluta="C://Repositorio//imagenes";
            byte[] bytesImg = imagen.getBytes();
            Path rutaCompleta=Paths.get(rutaAbsoluta+"//"+imagen.getOriginalFilename());
            Files.write(rutaCompleta,bytesImg);
            especialidad.setFoto(imagen.getOriginalFilename());
            Especialidad updatedEspecialidad = iEspecialidadService.UpdateEspecialidad(id, especialidad);
            response = new ApiResponse<>("Especialidad actualizada", updatedEspecialidad);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse<>("Error actualizando la especialidad: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("eliminar/{id}")
    public ResponseEntity<?> deleteEspecialidad(@PathVariable("id") Long id) {
        try {
            Especialidad especialidadEliminada = iEspecialidadService.DeleteEspecialidad(id);
            response = new ApiResponse<>("Especialidad eliminada", especialidadEliminada);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse<>("Error eliminando la especialidad: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("activar/{id}")
    public ResponseEntity<?> activateEspecialidad(@PathVariable("id") Long id) {
        try {
            Especialidad especialidadActivada =iEspecialidadService.ActivateEspecialidad(id);
            response = new ApiResponse<>("Especialidad activada", especialidadActivada);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response = new ApiResponse<>("Error activando la especialidad: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
