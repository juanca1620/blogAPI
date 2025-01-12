package com.juanca.blog.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.service.annotation.PutExchange;

import com.juanca.blog.payloads.Post.PostCreateDTO;
import com.juanca.blog.payloads.Post.PostResponseDTO;
import com.juanca.blog.payloads.Post.PostUpdateDTO;
import com.juanca.blog.services.IPostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	
	private final IPostService service;

	@Autowired
	public PostController(IPostService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/")
	public ResponseEntity<PostResponseDTO> postPost (@RequestBody @Valid PostCreateDTO dto){
		PostResponseDTO body = service.savePost(dto);
		return new ResponseEntity<PostResponseDTO>(body,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<PostResponseDTO>> getAll (){
		return ResponseEntity.ok(this.service.getAll());
	}
	
	@GetMapping("/{postID}")
	public ResponseEntity<PostResponseDTO> getPost (@PathVariable Integer postID){
		return ResponseEntity.ok(this.service.getPostById(postID));
	}
	
	@DeleteMapping("/{postID}")
	public ResponseEntity<?> deletePost (@PathVariable Integer postID){
		this.service.deletePost(postID);
		return ResponseEntity.ok(Map.of("post","post deleted succcefully"));
	}
	
	@PutMapping ("/{postID}")
	public ResponseEntity<PostResponseDTO> updatePost (@Valid @RequestBody PostUpdateDTO dto,@PathVariable Integer postID){
		return ResponseEntity.ok(this.service.updatePost(dto, postID));
	}
	
	@GetMapping("/user/{userID}")
	public ResponseEntity<List<PostResponseDTO>> getAllByUser (@PathVariable Integer userID){
		
		List<PostResponseDTO> userPosts = this.service.getAllByUser(userID);
		
		return ResponseEntity.ok(userPosts);
	}
	
	@GetMapping("/category/{categoryID}")
	public ResponseEntity<List<PostResponseDTO>> getAllByCategory (@PathVariable Integer categoryID){
		
		List<PostResponseDTO> categoryPosts = this.service.getAllByCategory(categoryID);
		
		return ResponseEntity.ok(categoryPosts);
	}
}
