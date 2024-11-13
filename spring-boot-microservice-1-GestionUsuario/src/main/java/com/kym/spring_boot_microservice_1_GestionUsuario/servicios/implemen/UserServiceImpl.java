package com.kym.spring_boot_microservice_1_GestionUsuario.servicios.implemen;

import com.kym.spring_boot_microservice_1_GestionUsuario.persistencia.entidades.User;
import com.kym.spring_boot_microservice_1_GestionUsuario.repositorios.UserRepository;
import com.kym.spring_boot_microservice_1_GestionUsuario.servicios.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public List<User> ListUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public User SaveUser(User user)
    {
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        user.setFechaCreacion(LocalDateTime.now());
        User usuarioCreated= userRepository.save(user);
        return usuarioCreated;
    }

    @Override
    public Optional<User> findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }


    @Override
    public ApiResponse<User> modificarUsuario(@PathVariable(name="idUser") Long idUser, @RequestBody User usuarioActualizado) {
        Optional<User> usuario = userRepository.findById(idUser);
        if(usuario.isPresent())
        {
            User usuarioUpdate = usuario.get();
            usuarioUpdate.setUsername(usuarioActualizado.getUsername());
            usuarioUpdate.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
            usuarioUpdate.setNombre(usuarioActualizado.getNombre());
            usuarioUpdate.setApellido(usuarioActualizado.getApellido());
            usuarioUpdate.setTelefono(usuarioActualizado.getTelefono());
            usuarioUpdate.setEmail(usuarioActualizado.getEmail());
            usuarioUpdate.setFechaCreacion(LocalDateTime.now());
            usuarioUpdate.setRole(usuarioActualizado.getRole());
            usuarioUpdate.setEstado(usuarioActualizado.getEstado());
            User userActualizado=userRepository.save(usuarioUpdate);
            return new ApiResponse<>("Usuario Actualizado exitosamente",userActualizado);
        }
        else
        {
            throw new EntityNotFoundException();
        }

    }

    @Override
    public ApiResponse<User> deleteUser (@PathVariable("idUser") Long idUser)
    {
        Optional<User> user = userRepository.findById(idUser);
        if(user.isPresent())
        {
            userRepository.deleteUserEstado(idUser);

            Optional<User> userActualizado= userRepository.findById(idUser);
            User usuarioEliminado=userActualizado.get();
            return new ApiResponse<>("Usuario eliminado exitosamente",usuarioEliminado);
        }
        else
        {
            throw new EntityNotFoundException("Usuario con id " + idUser + " no encontrado");
        }
    }

    @Override
    public ApiResponse<User> activeUser(@PathVariable("idUser") Long idUser) {
        Optional<User> user = userRepository.findById(idUser);

        if (user.isPresent()) {

            userRepository.activeUserEstado(idUser);

            Optional<User> userActualizado = userRepository.findById(idUser);
            User usuarioActivado = userActualizado.get();
            return new ApiResponse<>("Usuario activado exitosamente", usuarioActivado);
        } else {
            throw new EntityNotFoundException("Usuario con id " + idUser + " no encontrado");
        }
    }

}
