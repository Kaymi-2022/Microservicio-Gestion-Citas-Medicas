package com.kym.spring_boot_microservice_5_ProcesoReservaCita.Model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="username", unique = true, nullable = false, length = 100)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="apellido", nullable = false)
    private String apellido;

    @Column(name="telefono", nullable = false)
    private String telefono;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Role role;

    @Column(name="genero", nullable = false)
    private String genero;

    @Column(name="estado", nullable = false)
    private int estado;

    @OneToMany(mappedBy = "usuario")
    private List<Citas> citas;

    @Transient
    private String token;


}