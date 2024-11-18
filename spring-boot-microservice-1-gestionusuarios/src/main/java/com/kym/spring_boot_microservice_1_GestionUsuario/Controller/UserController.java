package com.kym.spring_boot_microservice_1_GestionUsuario.Controller;

import com.kym.spring_boot_microservice_1_GestionUsuario.persistencia.entidades.User;
import com.kym.spring_boot_microservice_1_GestionUsuario.servicios.IUserService;
import com.kym.spring_boot_microservice_1_GestionUsuario.servicios.implemen.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/api/Gestionusuarios")
public class UserController {

    @Autowired
    IUserService iUserService;

    @GetMapping("listar")
    public ResponseEntity<?> getUsers() {
        try {
            List<User> listarUsuarios = iUserService.ListUsers();
            return ResponseEntity.ok(listarUsuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar los usuarios: " + e.getMessage());
        }
    }
    
    @PostMapping("guardar")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return ResponseEntity.ok(iUserService.SaveUser(user));
    }
    
    @PutMapping("change/{idUser}")
    public ResponseEntity<?> updateUser(@PathVariable Long idUser, @RequestBody User usuario) {
        return ResponseEntity.ok(iUserService.modificarUsuario(idUser, usuario));
    }

    @PutMapping("/eliminar/{idUser}")
    public ResponseEntity<ApiResponse<User>> deleteUser(@PathVariable Long idUser) {
        try {
            ApiResponse<User> respuesta = iUserService.deleteUser(idUser);
            return ResponseEntity.ok(respuesta);
        } catch (EntityNotFoundException e) {
            ApiResponse<User> errorResponse = new ApiResponse<>(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PutMapping("/activar/{idUser}")
    public ResponseEntity<ApiResponse<User>> activeUser(@PathVariable("idUser") Long idUser) {
        ApiResponse<User> saveUser = iUserService.activeUser(idUser);
        return ResponseEntity.ok(saveUser);
    }


}
