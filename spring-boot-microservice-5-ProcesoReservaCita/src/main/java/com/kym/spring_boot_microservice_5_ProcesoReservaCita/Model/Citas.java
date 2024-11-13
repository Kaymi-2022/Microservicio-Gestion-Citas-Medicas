package com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model;

import javax.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "citas")
public class Citas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "id_horario", nullable = false)
    private Horarios horario;

    @ManyToOne
    @JoinColumn(name="id_genero", nullable = false)
    private Genero genero;

    @Column(name = "estado", nullable = false)
    private int estado;


}
