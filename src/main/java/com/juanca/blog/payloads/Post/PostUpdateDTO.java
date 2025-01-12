package com.juanca.blog.payloads.Post;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public final class PostUpdateDTO {


	@NotNull(message = "The post id is required")
	@Positive(message = "post id invalid")
	private Integer post_id;
	 
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
}
