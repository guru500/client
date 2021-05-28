package com.guru.client.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ClientServerException extends RuntimeException{
    private HttpStatus errorCode;

    private Map<String, String> errorMessages;

    public ClientServerException(Map<String, String> errorMessages, HttpStatus errorCode) {
        this.errorMessages = errorMessages;
        this.errorCode = errorCode;

    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }
}
