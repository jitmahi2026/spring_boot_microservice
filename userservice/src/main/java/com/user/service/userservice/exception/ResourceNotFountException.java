package com.user.service.userservice.exception;

public class ResourceNotFountException extends RuntimeException{
 
	public ResourceNotFountException() {
		super("Resource not fount on server !!");
	}
	public ResourceNotFountException(String message) {
		super(message);
	}
}
