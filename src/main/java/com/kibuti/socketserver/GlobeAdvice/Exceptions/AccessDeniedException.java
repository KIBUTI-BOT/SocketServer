package com.kibuti.socketserver.GlobeAdvice.Exceptions;

public class AccessDeniedException extends Exception{
    public AccessDeniedException(String message){
        super(message);
    }
}
