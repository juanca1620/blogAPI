package com.juanca.blog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juanca.blog.entities.User;
import com.juanca.blog.exceptions.ResourceNotFoundException;
import com.juanca.blog.payloads.UserDTO;
import com.juanca.blog.repositories.IUserRepository;

@Service
public class UserService implements IUserService{
	

	private IUserRepository repo;
	private ModelMapper mapper;

	@Autowired
	public UserService(IUserRepository repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		
		User user = dtoToUser(userDTO);
		
		user = this.repo.save(user);
		
		return userToDto(user);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		
		User user = this.repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		
		user.setAbout(userDTO.getAbout());
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		
	    User updatedUser = this.repo.save(user);
	    
	    return userToDto(updatedUser);
	}


	@Override
	public UserDTO getUserById(Integer userId) {
		User foundUser = this.repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		return userToDto(foundUser);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		
		List<User> users = this.repo.findAll();
		
		return users.stream().map(this::userToDto).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		this.repo.delete(user);
	}
	
	private User dtoToUser (UserDTO dto) {
		User user = mapper.map(dto, User.class);
		return user;
	}

	private UserDTO userToDto(User user) {
	    UserDTO dto = mapper.map(user,UserDTO.class);
	    
	    return dto;
	}


}
