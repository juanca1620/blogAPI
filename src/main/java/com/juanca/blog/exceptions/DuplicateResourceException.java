package com.juanca.blog.exceptions;

import lombok.Getter;

@Getter
public class DuplicateResourceException extends RuntimeException{

	private String object;
	
	private String field;

	public DuplicateResourceException(String object, String field) {
		super("This " + object + "  has an already existing " + " atribute , please try another one");
		this.object = object;
		this.field = field;
	}
	
}
