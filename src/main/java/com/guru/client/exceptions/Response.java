package com.guru.client.exceptions;

import java.util.Map;

public class Response {

    private Map<String,String> message;
    private String status;

    public Response() {
    }

    public Response(Map<String, String> message, String status) {
        this.message = message;
        this.status = status;
    }

    public Map<String, String> getMessage() {
        return message;
    }

    public void setMessage(Map<String, String> message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
