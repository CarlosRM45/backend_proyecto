package com.gustilandia.backend.exception;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gustilandia.backend.service.Response;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> customValidationError(MethodArgumentNotValidException exception){
		
		Response response = new Response();
		response.setResult(false);
		response.setMessage("Campos con datos no validos");
		
		List<String> listExceptions = exception.getBindingResult().getFieldErrors()
			.stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
		response.setResult(listExceptions);
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
	}
	
}
