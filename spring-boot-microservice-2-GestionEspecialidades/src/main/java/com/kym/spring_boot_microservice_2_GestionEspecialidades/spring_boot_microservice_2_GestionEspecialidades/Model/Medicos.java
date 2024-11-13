package com.kym.spring_boot_microservice_2_GestionEspecialidades.spring_boot_microservice_2_GestionEspecialidades.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data
@EqualsAndHashCode(of = "idMedico")
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