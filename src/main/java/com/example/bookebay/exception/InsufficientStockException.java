package com.example.bookebay.exception;

public class InsufficientStockException extends RuntimeException{
    public InsufficientStockException(String message){super(message);}
}
