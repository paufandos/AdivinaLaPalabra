package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.exceptions;

public class GameIsWinnedException extends Exception {

    public GameIsWinnedException(){
        super("La partida está ganada");
    }
}
