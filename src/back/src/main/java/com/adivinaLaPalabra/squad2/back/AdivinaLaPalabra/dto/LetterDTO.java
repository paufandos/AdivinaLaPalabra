package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto;

import lombok.Getter;

@Getter
public class LetterDTO {
    public enum Status {NOT_MATCHED, MATCHED, CONTAINED}

    private final char letter;

    private final Status status;

    private final int position;

    public LetterDTO(char letter, Status status, int position) {
        this.letter = letter;
        this.status = status;
        this.position = position;
    }

}
