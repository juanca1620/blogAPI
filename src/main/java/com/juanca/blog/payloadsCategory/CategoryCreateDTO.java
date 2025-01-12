package com.juanca.blog.payloadsCategory;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public final class CategoryCreateDTO {

	@NotNull(message = "The Category name is obligatory")
	@Size(max = 50,message = "The name has to be lower than 50 characters")
	private String categoryName;
	
	@Size(max = 100,message = "The description has to be lower than 1000 characters")
	private String categoryDescription;
}
