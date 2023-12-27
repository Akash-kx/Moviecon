package com.moviecon.InventoryProduct.MainExceptionHandler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class InventoryExceptionHandler {
	
	@ExceptionHandler(ConstraintViolationException.class)
	public String registerExceptionHandler(ConstraintViolationException ConstraintEx) {

		return "No parameter should be Empty";
	}
	
	@ExceptionHandler(MultipartException.class)
	public String registerExceptionHandler(MultipartException MultiPartEx) {

		String fileException = MultiPartEx.getMessage();
		return fileException;
	}

}
