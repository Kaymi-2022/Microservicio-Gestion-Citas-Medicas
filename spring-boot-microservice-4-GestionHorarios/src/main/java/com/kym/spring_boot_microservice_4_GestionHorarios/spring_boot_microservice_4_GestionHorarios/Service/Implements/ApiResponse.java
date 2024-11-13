package com.kym.spring_boot_microservice_4_GestionHorarios.spring_boot_microservice_4_GestionHorarios.Service.Implements;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponse<T> {
    private String message;
    private T data;
}
