package com.guru.client.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UniqueConstraintException.class)
    public ResponseEntity<Object> handleUniqueConstraintException(UniqueConstraintException ex, WebRequest request) {

        Response response = new Response(ex.getErrorMessages(), ex.getErrorCode().name());

        GenericResponse<Response> genericResponse = new GenericResponse<>(false, ex.getErrorCode().name(), response);

        return handleExceptionInternal(ex, genericResponse, new HttpHeaders(), ex.getErrorCode(), request);

    }

    @ExceptionHandler(ClientServerException.class)
    public ResponseEntity<Object> handleClientServerException(ClientServerException ex, WebRequest request) {
        Map<String, String> error = new HashMap<>();
        error.put("Error", ex.getMessage());
        Response response = new Response(error, HttpStatus.INTERNAL_SERVER_ERROR.name());

        GenericResponse<Response> genericResponse = new GenericResponse<>(false,
                HttpStatus.INTERNAL_SERVER_ERROR.name(), response);

        return handleExceptionInternal(ex, genericResponse, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.put(error.getObjectName(), error.getDefaultMessage());
        }
        Response response = new Response(errors, HttpStatus.BAD_REQUEST.name());

        GenericResponse<Response> genericResponse = new GenericResponse<>(false, HttpStatus.BAD_REQUEST.name(),
                response);

        return handleExceptionInternal(ex, genericResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        Map<String, String> error = new HashMap<>();
        error.put("Error", ex.getMessage());
        Response response = new Response(error, HttpStatus.INTERNAL_SERVER_ERROR.name());

        GenericResponse<Response> genericResponse = new GenericResponse<>(false,
                HttpStatus.INTERNAL_SERVER_ERROR.name(), response);

        return handleExceptionInternal(ex, genericResponse, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


}
