package com.juanca.blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.juanca.blog.entities.User;
import com.juanca.blog.payloads.UserDTO;
import com.juanca.blog.repositories.IUserRepository;

public class UserService implements IUserService{
	
	@Autowired
	private IUserRepository repo;

	@Override
	public UserDTO createUser(UserDTO user) {
		
		User userAux = dtoToUser(user);
		userAux = repo.save(userAux);
		
		user = userToDto(userAux);
		return user;
	}

	@Override
	public UserDTO updateUser(UserDTO user, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
	}
	
	private User dtoToUser (UserDTO dto) {
		User user = User.builder().about(dto.getAbout()).
				email(dto.getEmail()).
				id(dto.getId()).
				name(dto.getName()).
				password(dto.getPassword()).build();
		return user;
	}

	private UserDTO userToDto(User user) {
	    UserDTO dto = UserDTO.builder()
	            .about(user.getAbout())
	            .email(user.getEmail())
	            .id(user.getId())
	            .name(user.getName())
	            .build(); // Sin password
	    return dto;
	}


}
