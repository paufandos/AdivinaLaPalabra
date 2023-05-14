package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services.impl;

import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.LetterDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.LetterDTO.Status;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.entities.Game;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.exceptions.BadRequestException;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.exceptions.GameIsWinnedException;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.repositories.WordRepository;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.repositories.GameRepository;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services.IWordService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.w3c.dom.ranges.RangeException;

@Service
public class WordServiceImpl implements IWordService {

    private final static int START_WORD_LENGHT = 0;
    private final static int MAX_WORD_LENGHT = 5;

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Boolean checkIfWordExists(String requestWord) throws RuntimeException {
        return wordRepository.findByValue(requestWord) != null;
    }

    @Override
    public List<LetterDTO> validatePositions(String requestWord, UUID gameId) throws BadRequestException, GameIsWinnedException {


        checkIfIsBadWord(requestWord);
        Game game = gameRepository.getReferenceById(gameId);

        if (game.getWinned()) throw new GameIsWinnedException();
        List<LetterDTO> letters = new ArrayList<>();
        checkAttemptsInRange(game,game.getAttempts());
        String correctWord = game.getCorrectWord().getValue();

        IntStream.range(START_WORD_LENGHT, MAX_WORD_LENGHT).forEach(position -> {
            char tryWordLetter = requestWord.charAt(position);
            char correctWordLetter = correctWord.charAt(position);

            Status status = validateLetter(correctWord, tryWordLetter, correctWordLetter);
            LetterDTO letter = new LetterDTO(tryWordLetter, status, position);
            letters.add(letter);
        });
        checkGameWinned(letters,game);
        return letters;
    }

    public void checkGameWinned(List<LetterDTO> letters, Game game) {
        if(letters.stream().filter(letter -> letter.getStatus() == Status.MATCHED).count() == 5) {
            game.setWinned(true);
            gameRepository.save(game);
        }
    }

    private void updateGameAttempts(Game game) {
        game.setAttempts(game.getAttempts()+1);
        gameRepository.save(game);
    }

    public Status validateLetter(String correctWord, char tryWordLetter, char correctWordLetter) {
        if (tryWordLetter == correctWordLetter) return Status.MATCHED;
        return correctWord.indexOf(tryWordLetter) >= START_WORD_LENGHT ? Status.CONTAINED : Status.NOT_MATCHED;
    }

    public void checkIfIsBadWord(String requestWord) throws BadRequestException {
        if (!checkIfWordExists(requestWord)) {
            throw new BadRequestException(requestWord + " no existe.");
        }
    }

    public void checkAttemptsInRange(Game game, int attempts) throws RangeException {
        final int MAX_RANGE = 5;
        if(attempts>=MAX_RANGE) {
            throw new RangeException((short) HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE.value(),"Ha pasado el límite de intentos");
        }else updateGameAttempts(game);
    }
}
