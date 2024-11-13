package com.kym.spring_boot_microservice_3_GestionMedicos.spring_boot_microservice_3_GestionMedicos.Controller;

import java.nio.file.Paths;

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
import org.springframework.web.multipart.MultipartFile;
import com.kym.spring_boot_microservice_3_GestionMedicos.spring_boot_microservice_3_GestionMedicos.Model.Medicos;
import com.kym.spring_boot_microservice_3_GestionMedicos.spring_boot_microservice_3_GestionMedicos.Service.ServiceMedicos;
import com.kym.spring_boot_microservice_3_GestionMedicos.spring_boot_microservice_3_GestionMedicos.Service.Implements.ApiResponse;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("api/GestionMedicos")
public class MedicoController {

    @Autowired
    ServiceMedicos serviceMedicos;

    ApiResponse<?> apiResponse;

    @GetMapping("/listar")
    public ResponseEntity<?> listarMedicos() {
        try {
            return ResponseEntity.ok().body(serviceMedicos.listAllMedicos());
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("Error al listar los medicos", null);
            return ResponseEntity.status(400).body(apiResponse);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> listarMedicoById(Long id) {
        try {
            return ResponseEntity.ok().body(serviceMedicos.listMedicoById(id));
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("Error al listar el medico", null);
            return ResponseEntity.status(400).body(apiResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> addMedico(@ModelAttribute Medicos medico, @RequestParam("file") MultipartFile imagen,@RequestParam("idEspecialidad") Long idEspecialidad) {
        try {
            if (!imagen.isEmpty()) {
                String rutaAbsoluta = "C://Repositorio//imagenes";
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                medico.setFoto(imagen.getOriginalFilename());
            }
          
            return serviceMedicos.addMedico(medico, idEspecialidad);
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("Error al añadir el medico", null);
            return ResponseEntity.status(400).body(apiResponse);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> updateMedico(@PathVariable("id") Long id, @ModelAttribute Medicos medico,
            @RequestParam("file") MultipartFile imagen) {
        try {
            if (!imagen.isEmpty()) {
                String rutaAbsoluta = "C://Repositorio//imagenes";
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                medico.setFoto(imagen.getOriginalFilename());
            }
            return serviceMedicos.updateMedico(medico, id);
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("Error al actualizar el medico", null);
            return ResponseEntity.status(400).body(apiResponse);
        }
    }

    @DeleteMapping("/desactivar/{id}")
    public ResponseEntity<?> deleteMedico(@PathVariable("id") Long id) {
        try {
            return serviceMedicos.deleteMedico(id);
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("Error al eliminar el medico", null);
            return ResponseEntity.status(400).body(apiResponse);
        }
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<?> activeMedico(@PathVariable("id") Long id) {
        try {
            return serviceMedicos.activeMedico(id);
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("Error al activar el medico", null);
            return ResponseEntity.status(400).body(apiResponse);
        }
    }

}
