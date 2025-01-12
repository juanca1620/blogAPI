package com.juanca.blog.services;

import java.util.List;

import com.juanca.blog.entities.Category;
import com.juanca.blog.entities.User;
import com.juanca.blog.payloads.Post.PostCreateDTO;
import com.juanca.blog.payloads.Post.PostResponseDTO;
import com.juanca.blog.payloads.Post.PostUpdateDTO;

import jakarta.persistence.PostUpdate;

public interface IPostService {

	PostResponseDTO savePost (PostCreateDTO dto);
	
	void deletePost (Integer id);
	
	PostResponseDTO getPostById (Integer id);
	
	List<PostResponseDTO> getAll ();
	
	List<PostResponseDTO> getAllByUser(Integer userID);
	
	List<PostResponseDTO> getAllByCategory(Integer categoryID);

	PostResponseDTO updatePost(PostUpdateDTO dto, Integer id);
}
