package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.exceptions.handlers;

import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.entities.Game;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.exceptions.GameIsWinnedException;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.exceptions.InsufficientGamesException;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.repositories.GameRepository;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.repositories.WordRepository;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services.impl.GameServiceImpl;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services.impl.UserServiceImpl;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services.impl.WordServiceImpl;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.utilities.DateUtils;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.UtilsHelper.*;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.GameHelper.*;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.AuthHelper.*;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.WordHelper.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(value = DEFAULT_USERNAME)
public class ErrorHandlerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    GameServiceImpl gameService;

    @MockBean
    UserServiceImpl userServiceImpl;

    @MockBean
    GameRepository gameRepository;

    @MockBean
    WordRepository wordRepository;

    @Mock
    WordServiceImpl wordService;

    @Test
    void testHandleBadURLRequestMustReturn404Status() throws Exception {
        final String NON_EXISTENT_URL = "/NotNonexistentURL";
        this.mockMvc.perform(MockMvcRequestBuilders.get(NON_EXISTENT_URL))
                .andExpect(status().isNotFound());
    }

    @Test
    void testHandleIncompleteURLRequestMustReturn404Status() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(VALIDATE_POSITIONS_URL))
                .andExpect(status().isNotFound());
    }

    @Test
    void testHandleEntityNotFoundExceptionMustReturn404Status() throws Exception {
        when(gameService.getCorrectWord(GAME_ID)).thenThrow(new EntityNotFoundException("Partida con id " + GAME_ID + "no encontrada"));
        this.mockMvc.perform(MockMvcRequestBuilders.get(GET_CORRECT_WORD_URL + GAME_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    void testHandleWinnedGameMustReturnWinnedGameExceptionMustReturn406Status() throws Exception {
        Game game = new Game(GAME_ID,EXISTING_WORD_IN_THE_DICTIONARY, USER,DateUtils.generateLocalDateTimeNow(),3,true);
        when(wordService.validatePositions(EXISTENT_WORD,game.getId())).thenThrow(new GameIsWinnedException());

        assertThatThrownBy(() -> wordService.validatePositions(EXISTENT_WORD,game.getId()))
                .isInstanceOf(GameIsWinnedException.class)
                .hasMessageStartingWith("La partida está ganada");
    }

    @Test
    void testHandleInsufficientGamesExceptionMustReturn406Status() throws Exception {
        when(gameService.getAllGames(DEFAULT_USERNAME)).thenThrow(new InsufficientGamesException());
        this.mockMvc.perform(MockMvcRequestBuilders.get(GET_ALL_GAMES_URL)
                .header(AUTH_HEADER, AUTH_TOKEN_HEADER))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    void testHandleRangeExceptionMustReturn416Status() throws Exception {
        Game game = new Game(GAME_ID,EXISTING_WORD_IN_THE_DICTIONARY, USER,DateUtils.generateLocalDateTimeNow(),7,false);
        when(wordRepository.findByValue(EXISTENT_WORD)).thenReturn(EXISTING_WORD_IN_THE_DICTIONARY);
        when(gameRepository.getReferenceById(game.getId())).thenReturn(game);

        this.mockMvc.perform(MockMvcRequestBuilders.post(VALIDATE_POSITIONS_URL + game.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(POSITIONS_REQUEST)))
                .andExpect(status().isRequestedRangeNotSatisfiable());
    }

    @Test
    void testHandleBadCredentialsExceptionMustReturn401Status() throws Exception {
        // TO DO
    }

    @Test
    void testHandleUsernameNotFoundExceptionMustReturn401Status() throws Exception {
        // TO DO
    }

    @Test
    void testHandleHttpMessageNotReadableExceptionMustReturn400Status() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(VALIDATE_POSITIONS_URL + GAME_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testHandleBadRequestExceptionMustReturn400Status() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(VALIDATE_POSITIONS_URL + BAD_GAME_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(POSITIONS_REQUEST)))
                .andExpect(status().isBadRequest());
    }

}
