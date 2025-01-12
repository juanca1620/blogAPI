package com.juanca.blog.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juanca.blog.entities.Category;
import com.juanca.blog.entities.Post;
import com.juanca.blog.entities.User;
import com.juanca.blog.exceptions.ResourceNotFoundException;
import com.juanca.blog.payloads.UserDTO;
import com.juanca.blog.payloads.Post.PostCreateDTO;
import com.juanca.blog.payloads.Post.PostResponseDTO;
import com.juanca.blog.payloads.Post.PostUpdateDTO;
import com.juanca.blog.payloadsCategory.CategoryResponseDTO;
import com.juanca.blog.repositories.ICategoryRepository;
import com.juanca.blog.repositories.IPostRepository;
import com.juanca.blog.repositories.IUserRepository;

import jakarta.persistence.PostUpdate;

@Service
public class PostService implements IPostService{

	private final IPostRepository postRepository;
	
	private final IUserRepository userRepository;
	
	private final ICategoryRepository categoryRepository;
	
	private final ModelMapper mapper;
	
	
	@Autowired
	public PostService(IPostRepository postRepository, IUserRepository userRepository, ICategoryRepository categoryRepository,
			ModelMapper mapper) {
		super();
		this.postRepository = postRepository;
		this.userRepository = userRepository;
		this.categoryRepository = categoryRepository;
		this.mapper = mapper;
	}

	@Override
	public PostResponseDTO savePost(PostCreateDTO dto) {
		
		System.out.println(dto.toString());
		
		Post resp = postCreateDTOToPost(dto);
		
		Post saved = this.postRepository.save(resp);
		
		return postToPostResponseDTO(saved);
	}


	@Override
	public PostResponseDTO updatePost(PostUpdateDTO dto,Integer id) {
		Post post = this.postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "post id",id));
		post.setContent(dto.getContent());
		post.setImageName(dto.getImageName());
		post.setTitle(dto.getTitle());
		Post setedPost = this.postRepository.save(post);
		return postToPostResponseDTO(setedPost);
	}

	@Override
	public void deletePost(Integer id) {
		Post post = this.postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "post id",id));
		
		this.postRepository.deleteById(id);
	}

	@Override
	public PostResponseDTO getPostById(Integer id) {
		
		Post post = this.postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "post id",id));
		
		return postToPostResponseDTO(post);
	}

	@Override
	public List<PostResponseDTO> getAll() {
		List<Post> posts = this.postRepository.findAll();
		
		List<PostResponseDTO> postDTOS = posts.stream().map(this :: postToPostResponseDTO).collect(Collectors.toList());
		return postDTOS;
	}

	@Override
	public List<PostResponseDTO> getAllByUser(Integer userID) {
		User user = this.userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", "user id",userID));
		
		List<Post> userPosts = this.postRepository.findAllByUser(user);
		
		List <PostResponseDTO> response = userPosts.stream().map(this :: postToPostResponseDTO).collect(Collectors.toList());
		
		return response;
	}

	@Override
	public List<PostResponseDTO> getAllByCategory(Integer categoryID) {
		
		Category category = this.categoryRepository.findById(categoryID).orElseThrow(() -> new ResourceNotFoundException("Category", "category id",categoryID));
		
		List<Post> categoryPosts = this.postRepository.findAllByCategory(category);
		
		List <PostResponseDTO> response = categoryPosts.stream().map(this :: postToPostResponseDTO).collect(Collectors.toList());
		
		return response;
	}

	private Post postCreateDTOToPost (PostCreateDTO dto) {
		
		int userId = dto.getUser_id();
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id",userId));
		
		int categoryId = dto.getCategory_id();
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		
		Post response = new Post();
		
		response.setCategory(category);
		
		response.setContent(dto.getContent());
		
		response.setImageName(dto.getImageName());
		
		response.setTitle(dto.getTitle());
		
		response.setUser(user);
				
		response.setAddedDate(LocalDateTime.now());
		

		response.setCategory(category);
		
		response.setUser(user);
		
		return response;
	}
	
	private PostResponseDTO postToPostResponseDTO (Post post) {
		
		PostResponseDTO resp = mapper.map(post, PostResponseDTO.class);
		
		CategoryResponseDTO category = categoryToCategoryResponseDTO(post.getCategory());
		
		UserDTO user = userToDto(post.getUser());
		
		resp.setCategory(category);
		
		resp.setUser(user);
		
		return resp;
		
	}
	
	private CategoryResponseDTO categoryToCategoryResponseDTO (Category category) {
		return mapper.map(category, CategoryResponseDTO.class);
	}
	
	private UserDTO userToDto(User user) {
	    UserDTO dto = mapper.map(user,UserDTO.class);
	    
	    return dto;
	}

}
