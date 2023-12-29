package com.in.ExceptionHandler;

//This is the custom exceptions creation for any type of exception

public class UserNotFoundException extends Exception {

	public UserNotFoundException(String message) {
		super(message);
	}

}
