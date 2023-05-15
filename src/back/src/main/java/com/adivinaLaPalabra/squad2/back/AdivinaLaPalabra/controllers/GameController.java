package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.controllers;

import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.*;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.exceptions.InsufficientGamesException;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.security.jwt.JwtUtils;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services.impl.GameServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class GameController {

    @Autowired
    private GameServiceImpl gameService;

    @Autowired
    private JwtUtils jwtUtils;
    @ExceptionHandler({ InvalidDataAccessResourceUsageException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ErrorResponseDTO handleDatabaseExceptions(InvalidDataAccessResourceUsageException e) {
        e.printStackTrace();
        return new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ha habido un fallo al generar la partida, ya se ve lo loser que eres. Recarga anda.");
    }

    @GetMapping("/newGame")

    private GameDTO newGame(@RequestHeader(name = "Authorization") String authHeader) {
        log.info("Request to newGame");
        String username = jwtUtils.getUsernameFromAuthHeader(authHeader);
        return new GameDTO(gameService.newGame(username).getId());
    }

    @GetMapping("/getCorrectWord/{game_id}")
    private CorrectWordDTO getCorrectWord(@PathVariable("game_id") UUID gameId) {
        log.info("Request to getCorrectWord GameId : " + gameId);
        return gameService.getCorrectWord(gameId);
    }

    @GetMapping("/getTopThreeGames")
    private List<GameHistoryDTO> getTopThreeGames(@RequestHeader (name="Authorization") String authHeader) throws InsufficientGamesException {
        log.info("Request to getTopThreeGames");
        String username = jwtUtils.getUsernameFromAuthHeader(authHeader);
        return gameService.getTopThreeGames(username);
    }

    @GetMapping("/checkAttemptsInRange/{game_id}")
    private CheckAttemptsInRangeDTO checkAttemptsInRange(@PathVariable("game_id") UUID gameId) {
        log.info("Request to checkAttemptsInRange GameId : " + gameId);
        return gameService.checkFiveAttempts(gameId);
    }

    @GetMapping("/getLastTenGames")
    private LastTenGamesResponseDTO getLastTenGames(@RequestHeader(name = "Authorization") String authHeader)
            throws InsufficientGamesException {
        log.info("Request to getLastTenGames");
        String username = jwtUtils.getUsernameFromAuthHeader(authHeader);
        List<GameHistoryDTO> games = gameService.getLastTenGames(username);
        Boolean hasEnoughGames = gameService.hasEnoughGames(username);
        return new LastTenGamesResponseDTO(hasEnoughGames, games);
    }

    @GetMapping("/getAllGames")
    private List<GameHistoryDTO> getAllGames(@RequestHeader(name = "Authorization") String authHeader)
            throws InsufficientGamesException {
        log.info("Request to getLastTenGames");
        String username = jwtUtils.getUsernameFromAuthHeader(authHeader);
        return gameService.getAllGames(username);
    }
}
