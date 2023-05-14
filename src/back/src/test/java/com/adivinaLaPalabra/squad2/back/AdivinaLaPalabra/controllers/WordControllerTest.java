package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.controllers;

import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.LetterDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services.impl.WordServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.request.ValidatePositionsRequest;
import java.util.List;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.UtilsHelper.*;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.WordHelper.*;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.GameHelper.*;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.AuthHelper.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(value = DEFAULT_USERNAME)
public class WordControllerTest {
        @Autowired
        MockMvc mockMvc;

        @MockBean
        WordServiceImpl wordService;

        @Test
        void testCheckIfwordExtistMustReturnOKStatus() throws Exception {
                this.mockMvc.perform(MockMvcRequestBuilders.get(CHECK_IF_WORD_EXISTS_URL))
                                .andExpect(status().isOk());
        }

        @Test
        public void testEndPointValidatePositionsReturnOkStatus() throws Exception {
                ValidatePositionsRequest requestBody = new ValidatePositionsRequest('h', 'a', 'l', 'l', 'a');
                final String REQUEST_WORD = "halla";
                final List<LetterDTO> EXPECTED_LIST = List.of(
                                new LetterDTO('h', LetterDTO.Status.NOT_MATCHED, 0),
                                new LetterDTO('a', LetterDTO.Status.MATCHED, 1),
                                new LetterDTO('l', LetterDTO.Status.NOT_MATCHED, 2),
                                new LetterDTO('l', LetterDTO.Status.NOT_MATCHED, 3),
                                new LetterDTO('a', LetterDTO.Status.MATCHED, 4));

                when(wordService.validatePositions(REQUEST_WORD, GAME_ID)).thenReturn(EXPECTED_LIST);

                this.mockMvc.perform(MockMvcRequestBuilders.post(VALIDATE_POSITIONS_URL + GAME_ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(requestBody)))
                                .andExpect(status().isOk())
                                .andExpect(content().json(EXPECTED_LIST_DATA));
                ;
        }

}
