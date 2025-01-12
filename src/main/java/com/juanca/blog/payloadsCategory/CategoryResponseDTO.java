package com.juanca.blog.payloadsCategory;

import lombok.Data;

@Data
public final class CategoryResponseDTO {
	private Integer id;

	private String categoryName;
	
	private String categoryDescription;
}
