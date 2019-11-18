package com.example.got.exceptions;

public class TooManyRequestsException extends Exception {
    public TooManyRequestsException(String message){
        super(message);
    }
}
