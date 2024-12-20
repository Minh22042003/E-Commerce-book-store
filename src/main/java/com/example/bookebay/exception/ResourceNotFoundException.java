package com.example.bookebay.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String e){
        super(e);
    }
}
