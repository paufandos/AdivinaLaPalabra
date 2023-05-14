package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.utilities;

import java.util.Base64;

public class Base64Utils {

    public static String decode(String codeString) {
        return new String(Base64.getDecoder().decode(codeString.getBytes()));
    }

    public static String encode(String codeString) {
        return Base64.getEncoder().encodeToString(codeString.getBytes());
    }

}
