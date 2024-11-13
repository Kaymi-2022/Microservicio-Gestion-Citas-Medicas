package com.kym.spring_boot_microservice_3_GestionMedicos.spring_boot_microservice_3_GestionMedicos.Service;

import com.kym.spring_boot_microservice_3_GestionMedicos.spring_boot_microservice_3_GestionMedicos.Model.Medicos;
import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ServiceMedicos {

    public List<Medicos> listAllMedicos();

    public Medicos listMedicoById(Long id);

    public ResponseEntity<?> addMedico(Medicos medico, Long idEspecialidad);

    public ResponseEntity<?> updateMedico(Medicos medico, Long id);

    public ResponseEntity<?> deleteMedico(Long id);

    public ResponseEntity<?> activeMedico(Long id);

}
