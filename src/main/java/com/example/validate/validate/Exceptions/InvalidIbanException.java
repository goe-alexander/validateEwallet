package com.example.validate.validate.Exceptions;

public class InvalidIbanException extends RuntimeException{
    public InvalidIbanException(String message) {
        super(message);
    }
}
