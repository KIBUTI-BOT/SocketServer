package com.kibuti.socketserver.GlobeAdvice.Exceptions;

public class TokenExpiredException extends Exception{
    public TokenExpiredException(String message){
        super(message);
    }
}
