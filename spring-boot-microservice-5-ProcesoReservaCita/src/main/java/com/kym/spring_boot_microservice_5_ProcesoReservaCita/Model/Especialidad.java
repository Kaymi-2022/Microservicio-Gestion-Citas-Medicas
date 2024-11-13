package com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@ToString
@Entity
@Data
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

    @ToString.Exclude // Excluye para evitar recursión
    @EqualsAndHashCode.Exclude // Excluye para evitar recursión
    @OneToMany(mappedBy = "especialidad")
    private List<Medicos> medicos;

}