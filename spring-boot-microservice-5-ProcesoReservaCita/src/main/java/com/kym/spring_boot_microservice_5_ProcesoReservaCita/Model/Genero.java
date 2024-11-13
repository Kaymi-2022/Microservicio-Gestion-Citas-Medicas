package com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "genero")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genero;

    @OneToMany(mappedBy = "genero")
    private List<Citas> citas;

}
