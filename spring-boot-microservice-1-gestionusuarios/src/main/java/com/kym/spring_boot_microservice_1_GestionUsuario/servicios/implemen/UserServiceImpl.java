package com.kym.spring_boot_microservice_1_GestionUsuario.servicios.implemen;

import com.kym.spring_boot_microservice_1_GestionUsuario.persistencia.entidades.User;
import com.kym.spring_boot_microservice_1_GestionUsuario.repositorios.UserRepository;
import com.kym.spring_boot_microservice_1_GestionUsuario.servicios.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.net.http.HttpRequest;
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
    public List<User> ListUsers() {
        return userRepository.findAll();
    }

    @Override
    public ApiResponse<?> SaveUser(User user) {
        try {
            // Verificar si el username ya existe
            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                return new ApiResponse<>("El usuario con nombre de usuario " + user.getUsername() + " ya existe", null);
            }

            // Verificar si el email ya existe
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                return new ApiResponse<>("El usuario con email " + user.getEmail() + " ya existe", null);
            }

            // Encriptar la contraseña y establecer fecha de creación
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setFechaCreacion(LocalDateTime.now());

            // Intentar guardar el usuario
            User usuarioCreated = userRepository.save(user);

            return new ApiResponse<>("Usuario creado exitosamente", usuarioCreated);

        } catch (DataIntegrityViolationException e) {
            // Capturar violaciones de restricción de base de datos
            return new ApiResponse<>("Error de integridad: " + e.getRootCause().getMessage(), null);
        } catch (Exception e) {
            // Capturar cualquier otro error
            return new ApiResponse<>("Error al crear usuario: " + e.getMessage(), null);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public ApiResponse<User> modificarUsuario(@PathVariable(name = "idUser") Long idUser,
            @RequestBody User usuarioActualizado) {
        Optional<User> usuario = userRepository.findById(idUser);
        if (usuario.isPresent()) {
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
            usuarioUpdate.setGenero(usuarioActualizado.getGenero());
            User userActualizado = userRepository.save(usuarioUpdate);
            return new ApiResponse<>("Usuario Actualizado exitosamente", userActualizado);
        } else {
            return new ApiResponse<>("Usuario no encontrado", null);
        }

    }

    @Override
    @Transactional
    public ApiResponse<User> deleteUser(@PathVariable("idUser") Long idUser) {
        Optional<User> user = userRepository.findById(idUser);
        if (user.isPresent()) {
            // Actualiza el estado a "eliminado"
            userRepository.deleteUserEstado(idUser);
            return new ApiResponse<>("Usuario eliminado exitosamente", user.get());
        } else {
            return new ApiResponse<>("Usuario no encontrado", null);
        }
    }
    

    @Override
    @Transactional
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
