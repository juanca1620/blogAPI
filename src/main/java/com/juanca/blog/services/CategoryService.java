package com.juanca.blog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juanca.blog.entities.Category;
import com.juanca.blog.exceptions.DuplicateResourceException;
import com.juanca.blog.exceptions.ResourceNotFoundException;
import com.juanca.blog.payloadsCategory.CategoryCreateDTO;
import com.juanca.blog.payloadsCategory.CategoryResponseDTO;
import com.juanca.blog.payloadsCategory.CategoryUpdateDTO;
import com.juanca.blog.repositories.ICategoryRepository;

import lombok.AllArgsConstructor;

@Service
public class CategoryService implements ICategoryService{

	private final ICategoryRepository repo;
	
	private final ModelMapper mapper;
	
	@Autowired
	public CategoryService(ICategoryRepository repo,ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	@Override
	public CategoryResponseDTO saveCategory(CategoryCreateDTO dto) {
		Optional<Category> categoryFoundByName = this.repo.findByCategoryName(dto.getCategoryName());
		if(categoryFoundByName.isPresent()) {
			throw new DuplicateResourceException("Category", "Name");
		}
		Category category = categyCreateDTOToCategory(dto);
		Category saved = this.repo.save(category);
		return categoryToCategoryResponseDTO(category);
	}

	@Override
	public CategoryResponseDTO updateCategory(CategoryUpdateDTO dto,Integer id) {
		Category found = this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		Category category = categyUpdateDTOToCategory(dto);
		category.setId(found.getId());
		Category seted = this.repo.save(category);
		return mapper.map(seted, CategoryResponseDTO.class);
	}

	@Override
	public void delete(Integer id) {
		Category found = this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		this.repo.delete(found);
	}

	@Override
	public CategoryResponseDTO getCategoryById(Integer id) {
		Category found = this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		return mapper.map(found, CategoryResponseDTO.class);
	}

	@Override
	public List<CategoryResponseDTO> getAllCategories() {
		List<Category> categories = this.repo.findAll();
		
		List<CategoryResponseDTO> response  = categories.stream().map(this :: categoryToCategoryResponseDTO).collect(Collectors.toList());
				
		return response;
	}

	private Category categyCreateDTOToCategory (CategoryCreateDTO dto) {
		return mapper.map(dto, Category.class);
	}
	
	private Category categyUpdateDTOToCategory (CategoryUpdateDTO dto) {
		return mapper.map(dto, Category.class);
	}
	
	private CategoryCreateDTO categoryToCategoryCreateDTO(Category category) {
	    return mapper.map(category, CategoryCreateDTO.class);
	}

	private CategoryUpdateDTO categoryToCategoryUpdateDTO(Category category) {
	    return mapper.map(category, CategoryUpdateDTO.class);
	}
	
	private CategoryResponseDTO categoryToCategoryResponseDTO (Category category) {
		return mapper.map(category, CategoryResponseDTO.class);
	}

}
