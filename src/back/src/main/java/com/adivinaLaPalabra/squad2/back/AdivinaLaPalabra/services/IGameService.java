package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services;

import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.CheckAttemptsInRangeDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.CorrectWordDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.GameHistoryDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.entities.Game;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.exceptions.InsufficientGamesException;
import java.util.List;
import java.util.UUID;

public interface IGameService {

    public Game newGame(String username);

    public CorrectWordDTO getCorrectWord(UUID gameId);

    public CheckAttemptsInRangeDTO checkFiveAttempts(UUID gameId);

    List<GameHistoryDTO> getTopThreeGames(String username);

    public List<GameHistoryDTO> getLastTenGames(String username);

    public List<GameHistoryDTO> getAllGames(String username) throws InsufficientGamesException;

}
