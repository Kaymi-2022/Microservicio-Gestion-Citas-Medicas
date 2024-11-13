package com.kym.springbootmicroservice0apigateway.request;


import com.kym.springbootmicroservice0apigateway.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        value="compra-service",
        path="/api/Gestionusuarios",
        url="${GestionUsuarios.service.url}",
        configuration = FeignConfiguration.class
        )
public interface GestionUsuariosServiceRequest {

    @GetMapping("listar")
    public ResponseEntity<?> getUsers();

    @PutMapping("change/{idUser}")
    public ResponseEntity<?> updateUser(@PathVariable Long idUser, @RequestBody Object user);

    @PostMapping("guardar")
    public ResponseEntity<?> createUser(@RequestBody Object user) ;

    @PutMapping("/eliminar/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable Long idUser);

    @PutMapping("/activar/{idUser}")
    public ResponseEntity<?> activeUser(@PathVariable("idUser") Long idUser);
}
