package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services.impl;

import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.CheckAttemptsInRangeDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.CorrectWordDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.GameHistoryDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.entities.Game;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.exceptions.InsufficientGamesException;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.repositories.WordRepository;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.security.jwt.JwtUtils;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.repositories.GameRepository;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.GameHelper.*;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.WordHelper.*;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.AuthHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GameServiceImplTest {

    @InjectMocks
    GameServiceImpl gameService;

    @Mock
    GameRepository gameRepository;

    @MockBean
    WordServiceImpl wordService;

    @Mock
    WordRepository wordRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    UserServiceImpl userServiceImpl;

    @Mock
    JwtUtils jwtUtils;

    @Captor
    ArgumentCaptor<Game> captor;

    @Test
    void testNewGameMustReturnAnInt() {
        final Game NEW_GAME = new Game(EXISTING_WORD_IN_THE_DICTIONARY, DEFAULT_USER);
        final Game SAVE_GAME = new Game(EXISTING_WORD_IN_THE_DICTIONARY, DEFAULT_USER);
        SAVE_GAME.setId(GAME_ID);
        NEW_GAME.setId(GAME_ID);

        when(wordRepository.getReferenceById(anyInt())).thenReturn(EXISTING_WORD_IN_THE_DICTIONARY);
        when(wordRepository.count()).thenReturn(1L);
        when(gameRepository.save(captor.capture())).thenReturn(null);
        when(userServiceImpl.getUserByUsername(DEFAULT_USERNAME)).thenReturn(DEFAULT_USER);

        gameService.newGame(DEFAULT_USERNAME);
        Game saveGame = captor.getValue();

        assertEquals(EXISTING_WORD_IN_THE_DICTIONARY, saveGame.getCorrectWord());
    }

    @Test
    void testGetCorrectWordMustReturnCorrectWord() {
        final CorrectWordDTO EXPEXTED_WORD = new CorrectWordDTO(EXISTING_WORD_IN_THE_DICTIONARY.getValue());
        Game game = new Game(GAME_ID, EXISTING_WORD_IN_THE_DICTIONARY);
        when(gameRepository.getReferenceById(GAME_ID)).thenReturn(game);

        CorrectWordDTO correctWordDTO = gameService.getCorrectWord(GAME_ID);

        assertEquals(EXPEXTED_WORD.correctWord(), correctWordDTO.correctWord());
    }

    @ParameterizedTest
    @CsvSource(value = { "1,true", "4,true", "5,false", "10,false" })
    void testCheckFiveAttemptsMustReturnInRange(int attemptNumber, boolean expectedResult) {
        final CheckAttemptsInRangeDTO EXPECTED_DTO = new CheckAttemptsInRangeDTO(expectedResult);
        Game game = new Game(GAME_ID);
        game.setAttempts(attemptNumber);
        when(gameRepository.getReferenceById(GAME_ID)).thenReturn(game);
        CheckAttemptsInRangeDTO checkAttemptsInRangeDTO = gameService.checkFiveAttempts(GAME_ID);

        assertThat(checkAttemptsInRangeDTO).usingRecursiveComparison().isEqualTo(EXPECTED_DTO);
    }

    @Test
    void testGetTop3Games() {
        when(gameRepository.findTop3ByUser_IdAndWinnedTrueAndAttemptsGreaterThanOrderByAttemptsAsc(DEFAULT_USER.getId(), 0)).thenReturn(EXPECTED_TOP3_GAME_LIST);
        when(userServiceImpl.getUserByUsername(DEFAULT_USERNAME)).thenReturn(DEFAULT_USER);

        List<GameHistoryDTO> list = gameService.getTopThreeGames(DEFAULT_USERNAME);
        assertEquals(list.size(), EXPECTED_TOP3_GAME_HISTORY_LIST.size());
    }

        @Test
    void testGetLastTenGamesMustReturnLastTenGames() {
         when(gameRepository.findTop10ByUser_IdAndAttemptsGreaterThanOrderByDateDesc(DEFAULT_USER.getId(), 0)).thenReturn(EXPECTED_GAME_LIST);
         when(userServiceImpl.getUserByUsername(DEFAULT_USERNAME)).thenReturn(DEFAULT_USER);
         
         List<GameHistoryDTO> list = gameService.getLastTenGames(DEFAULT_USERNAME);

         assertEquals(list.size(),EXPECTED_GAME_HISTORY_LIST.size());
    }

    @Test
    void testGetAllGamesWithNoEnoughGamesMustReturnInsufficientGamesException() throws InsufficientGamesException {
        when(userServiceImpl.getUserByUsername(DEFAULT_USERNAME)).thenReturn(DEFAULT_USER);
        assertThatThrownBy(() -> gameService.getAllGames(DEFAULT_USERNAME))
                .isInstanceOf(InsufficientGamesException.class)
                .hasMessageStartingWith("No tiene suficientes partidas");
    }
}
