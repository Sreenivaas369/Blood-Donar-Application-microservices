package com.in.ExceptionHandler;

//This is the custom exceptions creation for any type of exception

public class InvalidPasswordException extends Exception {

	public InvalidPasswordException(String message) {
		super(message);
	}

}
