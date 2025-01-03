package com.juanca.blog.services;

import java.util.List;

import com.juanca.blog.payloads.UserDTO;

public interface IUserService {
	
    UserDTO createUser(UserDTO user);

    // Actualizar un usuario existente
    UserDTO updateUser(UserDTO user, Integer userId);

    // Obtener un usuario por su ID
    UserDTO getUserById(Integer userId);

    // Obtener una lista de todos los usuarios
    List<UserDTO> getAllUsers();

    // Eliminar un usuario por su ID
    void deleteUser(Integer userId);
}
