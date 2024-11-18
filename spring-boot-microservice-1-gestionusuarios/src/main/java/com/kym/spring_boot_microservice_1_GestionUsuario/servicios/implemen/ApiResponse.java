package com.kym.spring_boot_microservice_1_GestionUsuario.servicios.implemen;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponse<T> {
    private String mensaje;
    private T data;

}
