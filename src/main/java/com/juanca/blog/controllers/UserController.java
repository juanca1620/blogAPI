package com.juanca.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juanca.blog.payloads.UserDTO;
import com.juanca.blog.services.IUserService;
import com.juanca.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	

	private IUserService service;
	
	@Autowired
	public UserController(IUserService service) {
		super();
		this.service = service;
	}

	@PostMapping("/")
	
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO dto){
		UserDTO createdUser = this.service.createUser(dto);
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
	}
	
	@PutMapping("/{userID}")
	
	public ResponseEntity<UserDTO> updateUser (@Valid @RequestBody UserDTO dto,@PathVariable Integer userID){
		UserDTO updatedUser = this.service.updateUser(dto, userID);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uid) {
	    this.service.deleteUser(uid);
	    return new ResponseEntity<>(Map.of("menssage","user deleted succesfully"), HttpStatus.OK);
	}

	
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAll (){
		return new ResponseEntity<List<UserDTO>>(this.service.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/{userID}")
	public ResponseEntity<UserDTO> getUser (@PathVariable Integer userID){
		return new ResponseEntity<UserDTO>(this.service.getUserById(userID),HttpStatus.OK);
	}
}
