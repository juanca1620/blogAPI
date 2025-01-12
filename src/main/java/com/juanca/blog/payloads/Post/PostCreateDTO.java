package com.juanca.blog.payloads.Post;

import java.sql.Date;

import com.juanca.blog.entities.Category;
import com.juanca.blog.entities.User;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public final class PostCreateDTO {
	 
	@Size(max = 50,message = "The title has to has 50 characters or lower")
	@NotNull(message = "The title is required")
	@NotEmpty(message = "The title is required")
	private String title;
	
	@Size(max = 1000,message = "The content has to has 1000 characters or lower")
	@NotNull(message = "The content is required")
	@NotEmpty(message = "The content is required")
	private String content;
	
	@NotNull(message = "The image name is required")
	@Size(max = 200, message =  "the url is too long")
	private String imageName;
	
	@NotNull(message = "Category id is required")
	@Positive(message = "Invalid ID")
	private Integer category_id;
	
	
	@NotNull(message = "User id is required")
	@Positive(message = "Invalid ID")
	private Integer user_id;
}
