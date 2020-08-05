package com.example.validate.validate.Exceptions;

public class InexistentTransactionType extends RuntimeException{
    public InexistentTransactionType(String message) {
        super(message);
    }
}
