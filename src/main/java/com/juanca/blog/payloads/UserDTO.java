package com.juanca.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	
	private int id;
	
	@NotEmpty
	@Size(min = 5, message = "The minium username size is 5")
	private String name;
	
	@Email(message = "Invalid email")
	private String email;
	
	@Size(message = "Ur password is too short",min = 4)
	private String password;
	
	@Size(max = 1000, message = "Ur about is too long")
	private String about;
}
