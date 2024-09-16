package com.part_of_fa.usuarios_documentos.user.service;

import com.part_of_fa.usuarios_documentos.user.entity.User;
import com.part_of_fa.usuarios_documentos.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class UserService {
    private final UserRepository userRepository;



    // Definir el PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    Register
    public User register(User user) {
        // Encriptar la contraseña antes de guardar
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    //    Get
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

//    public User getUserById(Long id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
//    }

    //    Get All
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //    Delete
    public User deleteUserById(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return user;
    }

    //    Update
//    public User updateUser(User user) {
//        return userRepository.save(user);
//    }
    public User updateUser(User user) {
        // Asegurarse de que el usuario exista
        User existingUser = getUserById(user.getId());
        existingUser.setUsername(user.getUsername());
        // Actualizar otros campos según sea necesario
        return userRepository.save(existingUser);
    }





}
