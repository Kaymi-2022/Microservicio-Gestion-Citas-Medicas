package com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Data
@Table(name = "horarios")
public class Horarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El día no puede ser nulo")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dia;

    @NotNull(message = "La hora no puede ser nula")
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "idestadoCita",nullable = false)
    private EstadoCita idestadoCita;

    @ManyToOne
    @JoinColumn(name = "medico_id",nullable = false)
    private Medicos medicos;

}