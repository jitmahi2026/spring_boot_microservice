package com.product.service.productservice.exception;

public class ResourceNotFountException extends RuntimeException{
	 
		public ResourceNotFountException() {
			super("Resource not fount on server !!");
		}
		public ResourceNotFountException(String message) {
			super(message);
		}
	}
