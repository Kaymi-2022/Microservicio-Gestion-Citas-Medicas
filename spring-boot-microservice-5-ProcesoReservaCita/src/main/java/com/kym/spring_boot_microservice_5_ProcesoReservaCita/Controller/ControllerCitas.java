package com.kym.spring_boot_microservice_5_ProcesoReservaCita.Controller;

import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model.Citas;
import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Repository.EspecialidadRepository;
import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Repository.HorarioRepository;
import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Repository.MedicoRepository;
import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Repository.UserRepository;
import com.kym.spring_boot_microservice_5_ProcesoReservaCita.Service.ServiceCitas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ProcesoReservaCita")
public class ControllerCitas {

    @Autowired
    ServiceCitas serviceCitas;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    EspecialidadRepository especialidadRepository;

    @Autowired
    HorarioRepository horarioRepository;

    @GetMapping("/listar")
    public List<Citas> getCitas() {
        return serviceCitas.getCitas();
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearCita(@ModelAttribute Citas citaDTO) {

        return serviceCitas.saveCita(citaDTO);
    }

}
