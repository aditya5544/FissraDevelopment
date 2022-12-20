package com.v2stech.fissara.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.v2stech.fissara.exception.InvalidCredentialException;
import com.v2stech.fissara.exception.InvalidFileException;

@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandlerController {
	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<String> getInvalidCredentialException(InvalidCredentialException exception) {
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
//	@ExceptionHandler(InvalidFieldException.class)
//	public ResponseEntity<Map<String, String>> getInvalidFieldException(InvalidFieldException ex) {
//		Map<String, String> errorMessage = new HashMap<>();
//		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
//			String filedName = ((FieldError) error).getField();
//			String message = error.getDefaultMessage();
//			errorMessage.put(filedName, message);
//		}
//		return new ResponseEntity<Map<String, String>>(errorMessage, HttpStatus.NOT_FOUND);
//	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> globalException(Exception exception) {
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//		Map<String, String> resp = new HashMap<>();
//		ex.getBindingResult().getAllErrors().forEach((error)->{
//			String fieldName = ((FieldError) error).getField();
//			String message = error.getDefaultMessage();
//			resp.put(fieldName, message);
//		});
//		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
//	}
	@ExceptionHandler(InvalidFileException.class)
	public ResponseEntity<String> getIInvalidFileException(InvalidFileException exception) {
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
}
