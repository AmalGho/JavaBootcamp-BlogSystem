package com.example.blogsystem.Api;



public class ApiException extends RuntimeException {
    public ApiException (String msg) {
        super(msg);
    }
}
