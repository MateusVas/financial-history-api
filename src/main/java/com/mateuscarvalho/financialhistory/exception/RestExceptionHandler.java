package com.mateuscarvalho.financialhistory.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDetails> handlerBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(new ExceptionDetails(
                "Bad request exception, Check the parameters sent in the request.", "ERROR",
                LocalDateTime.now(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> notFoundRequestException(NotFoundException ex) {
        return new ResponseEntity<>(new ExceptionDetails(
                "Not found exception, The requested resource does not exist or cannot be found.",
                "ERROR", LocalDateTime.now(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ExceptionDetails> emptyResultDataAcessException(
            EmptyResultDataAccessException ex) {
        return new ResponseEntity<>(new ExceptionDetails(
                "Not found exception, The requested resource does not exist or cannot be found.",
                "ERROR", LocalDateTime.now(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionDetails> constraintViolationException(
            ConstraintViolationException ex) {
        return new ResponseEntity<>(new ExceptionDetails("The values entered are invalid!", "ERROR",
                LocalDateTime.now(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
