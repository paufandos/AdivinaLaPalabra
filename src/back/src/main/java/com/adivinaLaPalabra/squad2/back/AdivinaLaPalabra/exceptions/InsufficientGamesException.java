package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.exceptions;

public class InsufficientGamesException extends Exception{

    public InsufficientGamesException(){
        super("No tiene suficientes partidas");
    }
}
