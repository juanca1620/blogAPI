package com.juanca.blog.exceptions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundExceptionHandler (ResourceNotFoundException ex){
		return new ResponseEntity<>(Map.of("messague",ex.getMessage()),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> MethodArgumentNotValidExceptionHandler (MethodArgumentNotValidException ex){
		
		Map<String, String> resp =  new HashMap<>();
		ex.getBindingResult().getAllErrors().stream().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String cause = error.getDefaultMessage();
			resp.put(fieldName, cause);
		});
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<Map<String, String>> DuplicateResourceExceptionHandler (DuplicateResourceException ex){
		HashMap<String, String> response = new LinkedHashMap<>();
		response.put("message", ex.getMessage());
		response.put("object", ex.getObject());
		response.put("field", ex.getField());
		return ResponseEntity.badRequest().body(response);
	}
}
