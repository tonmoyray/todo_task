package com.example.demo.model.exception;

public class UserCredentialDoesNotMatchException extends RuntimeException{
    public UserCredentialDoesNotMatchException(String email) {
        super(email+ " credential not valid.");
    }
}
