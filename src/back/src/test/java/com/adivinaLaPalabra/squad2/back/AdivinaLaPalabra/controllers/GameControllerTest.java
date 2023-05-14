package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.controllers;

import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.CheckAttemptsInRangeDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.CorrectWordDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.entities.Game;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.repositories.GameRepository;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.repositories.WordRepository;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services.impl.GameServiceImpl;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services.impl.WordServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.GameHelper.*;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.WordHelper.*;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.AuthHelper.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(value = DEFAULT_USERNAME)
public class GameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GameServiceImpl gameService;

    @Mock
    GameRepository gameRepository;

    @InjectMocks
    WordServiceImpl wordService;

    @Mock
    WordRepository wordRepository;

    @Test
    void testEndpointNewGameMustReturnOK() throws Exception {
        when(gameService.newGame(DEFAULT_USERNAME)).thenReturn(GAME);

        this.mockMvc.perform(MockMvcRequestBuilders.get(NEW_GAME_URL)
                .header(AUTH_HEADER, AUTH_TOKEN_HEADER))
                .andExpect(status().isOk())
                .andExpect(content().json(NEW_GAME_EXPECTED_DATA));
    }

    @Test
    void testGetCorrectWordMustReturnCorrectWord() throws Exception {
        final CorrectWordDTO EXPEXTED_WORD = new CorrectWordDTO(EXISTENT_WORD);
        when(gameService.getCorrectWord(GAME_ID)).thenReturn(new CorrectWordDTO(EXISTENT_WORD));

        this.mockMvc.perform(MockMvcRequestBuilders.get(GET_CORRECT_WORD_URL + GAME_ID))
                .andExpect(status().isOk());
        CorrectWordDTO correctWordDTO = gameService.getCorrectWord(GAME_ID);

        assertEquals(EXPEXTED_WORD.correctWord(), correctWordDTO.correctWord());
    }

    @Test
    void testEndpointCheckAttemptsInRangeMustReturnOK() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(CHECK_ATTEMPTS_IN_RANGE_URL + GAME_ID))
                .andExpect(status().isOk());
    }

    @Test
    void testEndpointGetTopThreeGames() throws Exception {
        when(gameService.getTopThreeGames(DEFAULT_USERNAME)).thenReturn(EXPECTED_TOP3_GAME_HISTORY_LIST);
        this.mockMvc.perform(MockMvcRequestBuilders.get(GET_TOP3_GAMES_URL)
                        .header(AUTH_HEADER, AUTH_TOKEN_HEADER))
                .andExpect(status().isOk());
    }

    @Test
    void testEndpointGetLastTenGames() throws Exception {
        when(gameService.getLastTenGames(DEFAULT_USERNAME)).thenReturn(EXPECTED_GAME_HISTORY_LIST);

        this.mockMvc.perform(MockMvcRequestBuilders.get(GET_LAST_TEN_GAMES_URL)
                .header(AUTH_HEADER, AUTH_TOKEN_HEADER))
                .andExpect(status().isOk());
    }

    @Test
    void testEndpointGetAllGamesMustReturnOkStatus() throws Exception {
        when(gameService.getAllGames(DEFAULT_USERNAME)).thenReturn(EXPECTED_GAME_HISTORY_LIST);

        this.mockMvc.perform(MockMvcRequestBuilders.get(GET_ALL_GAMES_URL)
                .header(AUTH_HEADER, AUTH_TOKEN_HEADER))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @CsvSource(value = { "1,true", "4,true", "5,false", "10,false" })
    void testCheckAttemptsInRangeMustReturnInRange(int attemptNumber, boolean expectedResult) {
        final CheckAttemptsInRangeDTO EXPECTED_DTO = new CheckAttemptsInRangeDTO(expectedResult);
        Game game = new Game(GAME_ID);
        game.setAttempts(attemptNumber);

        when(gameService.checkFiveAttempts(GAME_ID)).thenReturn(EXPECTED_DTO);

        CheckAttemptsInRangeDTO checkAttemptsInRangeDTO = gameService.checkFiveAttempts(GAME_ID);
        assertThat(checkAttemptsInRangeDTO).usingRecursiveComparison().isEqualTo(EXPECTED_DTO);
    }
}
