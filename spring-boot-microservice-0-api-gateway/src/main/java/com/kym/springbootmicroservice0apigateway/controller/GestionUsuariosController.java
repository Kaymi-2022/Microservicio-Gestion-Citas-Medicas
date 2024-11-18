package com.kym.springbootmicroservice0apigateway.controller;

import com.kym.springbootmicroservice0apigateway.request.GestionUsuariosServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("gateway/gestionUsuarios")
public class GestionUsuariosController {

    @Autowired
    private GestionUsuariosServiceRequest gestionUsuariosServiceRequest;

    @GetMapping("listar")
    public ResponseEntity<?> getUsers()
    {
        return new ResponseEntity<>(gestionUsuariosServiceRequest.getUsers(), HttpStatus.OK);
    }

    @PutMapping("change/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Object user)
    {
        return new ResponseEntity<>(gestionUsuariosServiceRequest.updateUser(id, user), HttpStatus.OK);
    }

    @PostMapping("guardar")
    public ResponseEntity<?> createUser(@RequestBody Object user)
    {
        return new ResponseEntity<>(gestionUsuariosServiceRequest.createUser(user), HttpStatus.OK);
    }

    @PutMapping("eliminar/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable Long idUser)
    {
        return new ResponseEntity<>(gestionUsuariosServiceRequest.deleteUser(idUser), HttpStatus.OK);
    }


    @PutMapping("activar/{idUser}")
    public ResponseEntity<?> activeUser(@PathVariable("idUser") Long idUser)
    {
        return new ResponseEntity<>(gestionUsuariosServiceRequest.activeUser(idUser), HttpStatus.OK);
    }

}
