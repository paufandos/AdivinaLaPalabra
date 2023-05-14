package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.utilities;

public class NumberUtils {

    public static int generateRandomNumberInRange(int range) {
        return (int) (Math.random() * range) + 1;
    }
}
