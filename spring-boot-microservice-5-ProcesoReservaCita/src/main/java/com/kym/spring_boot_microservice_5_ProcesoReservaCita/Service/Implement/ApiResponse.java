package com.kym.spring_boot_microservice_5_ProcesoReservaCita.Service.Implement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private String mensaje;

    private T data;

}
