package com.kym.spring_boot_microservice_3_GestionMedicos.spring_boot_microservice_3_GestionMedicos.Service.Implements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import com.kym.spring_boot_microservice_3_GestionMedicos.spring_boot_microservice_3_GestionMedicos.Model.Especialidad;
import com.kym.spring_boot_microservice_3_GestionMedicos.spring_boot_microservice_3_GestionMedicos.Model.Medicos;
import com.kym.spring_boot_microservice_3_GestionMedicos.spring_boot_microservice_3_GestionMedicos.Respository.RepositoryEspecialidad;
import com.kym.spring_boot_microservice_3_GestionMedicos.spring_boot_microservice_3_GestionMedicos.Respository.RepositoryMedicos;
import com.kym.spring_boot_microservice_3_GestionMedicos.spring_boot_microservice_3_GestionMedicos.Service.ServiceMedicos;

@Service
public class ServiceMedicoImpl implements ServiceMedicos {

    @Autowired
    RepositoryMedicos medicoRepository;

    @Autowired
    RepositoryEspecialidad especialidadRepository;

    ApiResponse<Medicos> apiResponse;

    @Override
    public List<Medicos> listAllMedicos() {
        return medicoRepository.findAll();
    }

    @Override
    public Medicos listMedicoById(Long id) {
        return medicoRepository.findById(id).get();
    }

    @Transactional
    @Override
    public ResponseEntity<ApiResponse<?>> addMedico(Medicos medico,
            @RequestParam("idEspecialidad") Long idEspecialidad) {

        if (medicoRepository.existsByNombre(medico.getNombre())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("El médico ya existe", null));
        }
        Especialidad especialidad = especialidadRepository.findById(idEspecialidad)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Especialidad no encontrada con ID: " + idEspecialidad));

        medico.setEspecialidad(especialidad);
        Medicos medicoGuardado = medicoRepository.save(medico);

        return ResponseEntity.ok(new ApiResponse<>("Médico guardado exitosamente", medicoGuardado));
    }

    @Override
    public ResponseEntity<?> updateMedico(Medicos medico, Long id) {
        if (!medicoRepository.existsById(id)) {
            apiResponse = new ApiResponse<>("El medico no existe", null);
            return ResponseEntity.status(400).body(apiResponse);
        }
        Medicos medicoToUpdate = medicoRepository.findById(id).get();
        medicoToUpdate.setNombre(medico.getNombre());
        medicoToUpdate.setFoto(medico.getFoto());
        medicoToUpdate.setEstado(medico.getEstado());
        medicoToUpdate.setEspecialidad(medico.getEspecialidad());
        Medicos medicoUpdated = medicoRepository.save(medicoToUpdate);
        apiResponse = new ApiResponse<>("Medico actualizado", medicoUpdated);
        return ResponseEntity.ok().body(apiResponse);
    }

    @Override
    public ResponseEntity<?> deleteMedico(Long id) {
        if (!medicoRepository.existsById(id)) {
            apiResponse = new ApiResponse<>("El medico no existe", null);
            return ResponseEntity.status(400).body(apiResponse);
        }
        Medicos medicoToUpMedicos = medicoRepository.findById(id).get();
        medicoToUpMedicos.setEstado(0);
        Medicos medicoDeleted = medicoRepository.save(medicoToUpMedicos);
        apiResponse = new ApiResponse<>("Medico eliminado", medicoDeleted);
        return ResponseEntity.ok().body(apiResponse);
    }

    @Override
    public ResponseEntity<?> activeMedico(Long id) {
        if (!medicoRepository.existsById(id)) {
            apiResponse = new ApiResponse<>("El medico no existe", null);
            return ResponseEntity.status(400).body(apiResponse);
        }
        Medicos medicoToUpMedicos = medicoRepository.findById(id).get();
        medicoToUpMedicos.setEstado(1);
        Medicos medicoActive = medicoRepository.save(medicoToUpMedicos);
        apiResponse = new ApiResponse<>("Medico activado", medicoActive);
        return ResponseEntity.ok().body(apiResponse);
    }

}
