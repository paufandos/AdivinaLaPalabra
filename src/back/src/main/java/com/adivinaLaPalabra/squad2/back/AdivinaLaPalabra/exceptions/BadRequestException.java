package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.exceptions;

public class BadRequestException extends Exception{

    private static final String DESCRIPTION = "Bad Request";

    public BadRequestException(String detail){
        super(DESCRIPTION + " - " + detail);
    }
}
