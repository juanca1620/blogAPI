package com.juanca.blog.services;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.stereotype.Service;

import com.juanca.blog.payloadsCategory.CategoryCreateDTO;
import com.juanca.blog.payloadsCategory.CategoryResponseDTO;
import com.juanca.blog.payloadsCategory.CategoryUpdateDTO;

public interface ICategoryService {

	CategoryResponseDTO saveCategory (CategoryCreateDTO dto);
	
	CategoryResponseDTO updateCategory (CategoryUpdateDTO dto,Integer id);
	
	void delete (Integer id);
	
	CategoryResponseDTO getCategoryById(Integer id);
	
	List<CategoryResponseDTO> getAllCategories ();
}
