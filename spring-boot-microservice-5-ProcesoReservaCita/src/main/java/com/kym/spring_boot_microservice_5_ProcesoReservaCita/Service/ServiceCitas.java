package com.kym.spring_boot_microservice_5_ProcesoReservaCita.Service;

import java.util.List;

import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model.Citas;
import org.springframework.http.ResponseEntity;

public interface ServiceCitas {

    


    public List<Citas> getCitas();

    public ResponseEntity<?> saveCita(Citas cita);
    public ResponseEntity<?> getCita(Long id);
    
    public ResponseEntity<?> deleteCita(Long id);

    
    public ResponseEntity<?> updateCita(Citas cita);

    
    public ResponseEntity<?> getCitasByUser(Long id);
    


    public ResponseEntity<?> getCitasByMedico(Long id);

    ResponseEntity<?> getCitasByGenero(String genero);
}
