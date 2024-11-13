package com.kym.spring_boot_microservice_3_GestionMedicos.spring_boot_microservice_3_GestionMedicos.Service.Implements;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponse<T> {
    private String message;
    private T data;
}
