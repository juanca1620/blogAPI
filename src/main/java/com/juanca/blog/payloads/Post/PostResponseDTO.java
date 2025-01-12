package com.juanca.blog.payloads.Post;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.juanca.blog.payloads.UserDTO;
import com.juanca.blog.payloadsCategory.CategoryResponseDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public final class PostResponseDTO {
	
	private Integer post_id;

	private String title;
	
	private String content;
	
	private String imageName;
	
	private LocalDateTime addedDate;
	
	private CategoryResponseDTO category;
	
	private UserDTO user;
}
