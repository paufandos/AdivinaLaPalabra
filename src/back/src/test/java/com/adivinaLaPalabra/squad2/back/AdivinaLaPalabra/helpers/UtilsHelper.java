package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class UtilsHelper {

    public static final String DECODED_STRING = "paufandos";

    public static final String ENCODED_STRING = "cGF1ZmFuZG9z";

    public static String asJsonString(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
