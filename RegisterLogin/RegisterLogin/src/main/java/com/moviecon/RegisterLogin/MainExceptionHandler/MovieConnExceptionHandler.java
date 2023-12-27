package com.moviecon.RegisterLogin.MainExceptionHandler;

import java.util.HashMap;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MovieConnExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public HashMap<String, String> registerExceptionHandler(MethodArgumentNotValidException ex) {
		
		HashMap<String, String> errorMessage = new HashMap<String, String>();
		ex.getBindingResult().getFieldErrors().forEach( errors -> {
			
			errorMessage.put(errors.getField(), errors.getDefaultMessage());
		});		
		
		return errorMessage;
	}
}
