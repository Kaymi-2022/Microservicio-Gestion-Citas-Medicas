package com.kym.spring_boot_microservice_5_ProcesoReservaCita.Service.Implement;

import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model.Citas;
import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model.Genero;
import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model.Horarios;
import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model.User;
import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Repository.GeneroRepository;
import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Repository.HorarioRepository;
import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Repository.RepositoryCitas;
import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Repository.UserRepository;
import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Service.ServiceCitas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCitasImplem implements ServiceCitas {

    @Autowired
    RepositoryCitas repositoryCitas;

    @Autowired
    UserRepository repositoryUser;

    @Autowired
    HorarioRepository horarioRepository;

    @Autowired
    GeneroRepository generoRepository;

    @Override
    public List<Citas> getCitas() {
        return repositoryCitas.findAll();
    }

    @Override
    public ResponseEntity<?> saveCita(Citas citaDTO) {
        // Validar que los IDs sean válidos y buscar las entidades relacionadas
        if(!repositoryUser.existsById(citaDTO.getUsuario().getUserId())) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }

        if(!horarioRepository.existsById(citaDTO.getHorario().getId())) {
            return ResponseEntity.badRequest().body("Horario no encontrado");
        }

        if(!generoRepository.existsById(citaDTO.getGenero().getId())) {
            return ResponseEntity.badRequest().body("Genero no encontrado");
        }

        // Obtener las entidades relacionadas
        User usuario = repositoryUser.findById(citaDTO.getUsuario().getUserId()).get();
        Horarios horario = horarioRepository.findById(citaDTO.getHorario().getId()).get();
        Genero genero = generoRepository.findById(citaDTO.getGenero().getId()).get();

        // Crear la entidad Citas
        Citas cita = new Citas();
        cita.setUsuario(usuario);
        cita.setHorario(horario);
        cita.setGenero(genero);
        cita.setEstado(citaDTO.getEstado());

        // Verificar si ya existe una cita con el mismo usuario y horario
        if (repositoryCitas.existsByUsuarioAndHorario(cita.getUsuario(), cita.getHorario())) {
            return ResponseEntity.badRequest().body("Ya existe una cita en ese horario");
        }

        try {
            repositoryCitas.save(cita);
            return ResponseEntity.ok("Cita guardada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar la cita");
        }
    }



    @Override
    public ResponseEntity<?> getCita(Long id) {
        if (!repositoryCitas.existsById(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Error en proceso", "Cita no encontrada"));
        }
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new ApiResponse<>("Cita obtenida", repositoryCitas.findById(id).get()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Error en proceso", e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> deleteCita(Long id) {
        if (!repositoryCitas.existsById(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Error en proceso", "Cita no encontrada"));
        }
        try {
            repositoryCitas.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new ApiResponse<>("Cita eliminada", "Cita eliminada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Error en proceso", e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> updateCita(Citas cita) {
        if (!repositoryCitas.existsById(cita.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Error en proceso", "Cita no encontrada"));
        }
        try {
            repositoryCitas.save(cita);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new ApiResponse<>("Cita actualizada", repositoryCitas.findById(cita.getId()).get()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Error en proceso", e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> getCitasByUser(Long id) {
        if (!repositoryCitas.existsByUsuario(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Error en proceso", "Cita no encontrada"));
        }
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new ApiResponse<>("Citas obtenidas", repositoryCitas.findByUsuario(id)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Error en proceso", e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> getCitasByMedico (Long id){
        return null;
    }

    @Override
    public ResponseEntity<?> getCitasByGenero(String genero) {
        if (!repositoryCitas.existsByGenero(genero)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Error en proceso", "Cita no encontrada"));
        }

        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new ApiResponse<>("Citas obtenidas", repositoryCitas.findByGenero(genero)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Error en proceso", e.getMessage()));
        }
    }
}
