package com.juanca.blog.services;

import java.util.List;
import java.util.Optional;

import com.juanca.blog.payloads.UserDTO;

public interface IUserService {
	
    UserDTO createUser(UserDTO userDTO);

    // Actualizar un usuario existente
    UserDTO updateUser(UserDTO userDTO, Integer userId);

    // Obtener un usuario por su ID
    UserDTO getUserById(Integer userId);

    // Obtener una lista de todos los usuarios
    List<UserDTO> getAllUsers();

    // Eliminar un usuario por su ID
    void deleteUser(Integer userId);
}
