package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidatePositionsRequest {

    private final char pos0, pos1, pos2, pos3, pos4;

    public String wordSerialize() {
        StringBuilder word = new StringBuilder();
        char[] letters = { this.getPos0(), this.getPos1(), this.getPos2(), this.getPos3(), this.getPos4() };

        return word.append(letters).toString().toLowerCase();
    }

}
