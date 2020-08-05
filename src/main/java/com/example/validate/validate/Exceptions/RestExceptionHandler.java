package com.example.validate.validate.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Component
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(InvalidIbanException.class)
    public ResponseEntity InvalidIbanException(InvalidIbanException exception, HttpServletRequest request){
        return ResponseEntity.status(BAD_REQUEST).body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(InexistentTransactionType.class)
    public ResponseEntity handleInexistentTransactionType(InexistentTransactionType exception, HttpServletRequest request){
        return ResponseEntity.status(BAD_REQUEST).body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException exception, HttpServletRequest request){
        return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request){
        return ResponseEntity.status(BAD_REQUEST).body("Invalid Data In Body: " + getErrorMessage(exception));
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, HttpServletRequest request){
        return ResponseEntity.status(BAD_REQUEST).body("Invalid Json: " + exception.getLocalizedMessage());
    }

    private String getErrorMessage(MethodArgumentNotValidException exception) {
        return exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> String.format("[%s] %s", e.getField(), e.getDefaultMessage()))
                .collect(Collectors.joining("; "));
    }
}
