package com.javaacademy.cinema.controller;

import com.javaacademy.cinema.exception.AlreadyBoughtTicketException;
import com.javaacademy.cinema.exception.AlreadyExistsFilmException;
import com.javaacademy.cinema.exception.AlreadyExistsSessionException;
import com.javaacademy.cinema.exception.InvalidPassword;
import com.javaacademy.cinema.exception.NotFoundMovieException;
import com.javaacademy.cinema.exception.NotFoundPlaceException;
import com.javaacademy.cinema.exception.NotFoundSessionException;
import com.javaacademy.cinema.exception.NotFoundTicketException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidPassword.class)
    public ResponseEntity<?> handleInvalidPasswordException(InvalidPassword e) {
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({
            NotFoundMovieException.class,
            NotFoundSessionException.class,
            NotFoundTicketException.class,
            NotFoundPlaceException.class
    })
    public ResponseEntity<?> handle400Exception(RuntimeException e) {
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            AlreadyBoughtTicketException.class,
            AlreadyExistsFilmException.class,
            AlreadyExistsSessionException.class,
    })
    public ResponseEntity<?> handle405Exception(RuntimeException e) {
        log.warn(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
