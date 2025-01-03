package com.juanca.blog.payloads;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
	
	private int id;
	
	private String name;
	private String email;
	private String password;
	private String about;
}
