package com.kibuti.socketserver.GlobeAdvice.Exceptions;

public class TokenInvalidSignatureException extends Exception{
    public TokenInvalidSignatureException(String message){
        super(message);
    }
}
