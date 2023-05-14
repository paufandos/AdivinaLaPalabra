package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services;

import java.util.List;
import java.util.UUID;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.LetterDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.exceptions.BadRequestException;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.exceptions.GameIsWinnedException;

public interface IWordService {

    public Boolean checkIfWordExists(String word);

    public List<LetterDTO> validatePositions(String requestWord, UUID gameId) throws BadRequestException, GameIsWinnedException;

}
