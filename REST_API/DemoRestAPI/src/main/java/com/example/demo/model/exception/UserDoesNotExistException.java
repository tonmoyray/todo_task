package com.example.demo.model.exception;

public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException(String email) {
        super(email+ " does not exist.");
    }
}
