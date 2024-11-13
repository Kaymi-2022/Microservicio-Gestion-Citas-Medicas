package com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data
@Table(name = "medicos")
public class Medicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedico;

    @Column(name = "nombre", nullable = false)
    @NotNull(message = "El nombre no puede estar vacío")
    private String nombre;

    @Column(name = "foto_medico")
    private String foto;

    @Column(name = "estado")
    private int estado;

    @ManyToOne
    @JoinColumn(name = "especialida_id", nullable = false)
    private Especialidad especialidad;


}