package com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data
@EqualsAndHashCode(of = "id")
@Table(name = "especialidad")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    @NotNull(message = "El nombre no puede estar vacío")
    private String nombre;

    @Column(name = "foto")
    private String foto;

    @Column(name = "estado")
    private int estado;

    @OneToMany(mappedBy = "especialidad")
    private List<Medicos> medicos;
}