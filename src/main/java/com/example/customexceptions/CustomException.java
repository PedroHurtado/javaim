package com.example.customexceptions;

public class CustomException extends RuntimeException {
    public CustomException() {               
     this("No hay elementos");   
    }
    public CustomException(String message) {
        super(message);
    }
}
