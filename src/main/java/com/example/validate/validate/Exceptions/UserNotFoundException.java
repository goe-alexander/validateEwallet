package com.example.validate.validate.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long cnp) {
        super("Transaction can not be saved, user does not exist with cnp: " + cnp);
    }
}
