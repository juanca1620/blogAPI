package com.juanca.blog.controllers;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.juanca.blog.payloadsCategory.CategoryCreateDTO;
import com.juanca.blog.payloadsCategory.CategoryResponseDTO;
import com.juanca.blog.payloadsCategory.CategoryUpdateDTO;
import com.juanca.blog.services.ICategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	private final ICategoryService service;

	@Autowired
	public CategoryController(ICategoryService service, ModelMapper mapper) {
		super();
		this.service = service;
	}

	@PostMapping("/")

	public ResponseEntity<CategoryResponseDTO> createUser(@Valid @RequestBody CategoryCreateDTO dto) {
		CategoryResponseDTO createdUser = this.service.saveCategory(dto);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	
	@PutMapping("/{categoryID}")
	public ResponseEntity<CategoryResponseDTO> updateUser(@Valid @RequestBody CategoryUpdateDTO dto, @PathVariable Integer categoryID) {
		CategoryResponseDTO updatedUser = this.service.updateCategory(dto, categoryID);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{categoryID}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer categoryID) {
		this.service.delete(categoryID);
		return new ResponseEntity<>(Map.of("menssage", "Category deleted succesfully"), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<CategoryResponseDTO>> getAll() {
		List<CategoryResponseDTO> categories = this.service.getAllCategories();
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/{categoryID}")
	public ResponseEntity<CategoryResponseDTO> getUser(@PathVariable Integer categoryID) {
		CategoryResponseDTO found = this.service.getCategoryById(categoryID);
		return new ResponseEntity<CategoryResponseDTO>(found, HttpStatus.OK);
	}
	
}
