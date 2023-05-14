package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.controllers;

import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.CheckIfWordExistsDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.LetterDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.exceptions.BadRequestException;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.exceptions.GameIsWinnedException;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.request.ValidatePositionsRequest;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services.impl.WordServiceImpl;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
public class WordController {

    @Autowired
    private WordServiceImpl wordService;

    @GetMapping(path = "/checkIfWordExists/{word}")
    private CheckIfWordExistsDTO checkIfWordExists(@PathVariable("word") String word) {
        log.info("Request to checkIfWordExists - {}", word);
        return new CheckIfWordExistsDTO(wordService.checkIfWordExists(word));
    }

    @PostMapping("/validatePositions/{game_id}")
    private List<LetterDTO> validatePositions(@RequestBody ValidatePositionsRequest body, @PathVariable("game_id") UUID gameId) throws BadRequestException, GameIsWinnedException {
        log.info("Request to validatePositions - game_id: {}", gameId);
        return wordService.validatePositions(body.wordSerialize(), gameId);
    }
}