package com.in.ExceptionHandler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public String UserAlreadyExistAuthenticationException(DataIntegrityViolationException exception) {
		return exception.getMessage();

	}
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String UserNotFoundException(UserNotFoundException exception) {
		return exception.getMessage();

	}
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public String InvalidPasswordException(InvalidPasswordException exception) {
		return exception.getMessage();

	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String InvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException exception) {
		return exception.getMessage();

	}

	
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public Map<String, String> MethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<>();
		exception.getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});
		return errors;
	}


	
	@ExceptionHandler
	@ResponseBody
	public String IncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException exception) {
		return exception.getMessage();

	}

	
	@ExceptionHandler
	@ResponseBody
	public String IllegalArgumentException(IllegalArgumentException exception) {
		return exception.getMessage();
	}

	@ExceptionHandler
	public String MissingServletRequestParameterException(MissingServletRequestParameterException exception) {
		return exception.getMessage();

	}

	//for post method
	@ExceptionHandler
	public String HttpMessageNotReadableException(HttpMessageNotReadableException exception) {
		return exception.getMessage();

	}
	
	
	

}
