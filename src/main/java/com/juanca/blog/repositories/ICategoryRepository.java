package com.juanca.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juanca.blog.entities.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer>{
	
	Optional<Category> findByCategoryName (String categoryName);
}
