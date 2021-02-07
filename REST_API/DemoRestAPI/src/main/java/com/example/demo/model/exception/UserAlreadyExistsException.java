package com.example.demo.model.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String email) {
        super(email+ " already exists ");
    }
}
