package com.juanca.blog.exceptions;

import lombok.Data;

public class ResourceNotFoundException extends RuntimeException{

	private final String resourceName;
	private final String fieldName;
	private final  long fieldValue;
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super("Resource " + resourceName + " with the field name " + fieldName + " with the value " + fieldValue + " was not found");
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
}
