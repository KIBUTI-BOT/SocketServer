package com.kibuti.socketserver.GlobeAdvice.Exceptions;

public class PermissionDeniedException extends Exception{
    public PermissionDeniedException(String message){
        super(message);
    }
}
