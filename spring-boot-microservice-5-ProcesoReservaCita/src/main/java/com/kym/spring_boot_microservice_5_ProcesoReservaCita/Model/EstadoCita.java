package com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@Entity
@Data
@Table(name = "estadoCita")
public class EstadoCita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idestadoCita;

    @NotNull(message = "El estado de la cita no puede ser nulo")
    private int estado;

    @NotEmpty(message = "El estado de la cita no puede ser nulo")
    private String descripcion;
}
