package com.juanca.blog.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juanca.blog.entities.Category;
import com.juanca.blog.entities.Post;
import com.juanca.blog.entities.User;

public interface IPostRepository extends JpaRepository<Post, Integer>{
	
	List<Post> findAllByUser (User user);
	
	List<Post> findAllByCategory (Category category);
}
