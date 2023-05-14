package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.exceptions.handlers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.LoginDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services.impl.UserServiceImpl;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.AuthHelper.*;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.UtilsHelper.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ValidationHandlerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserServiceImpl userServiceImpl;


    @Test
    void testHandleMethodArgumentNotValidMustReturn400Status() throws Exception{
        final LoginDTO MALFORMED_USER = new LoginDTO("TXpJeA==", "TXpJeA==");
        
        when(userServiceImpl.validateUser(USER_REQUEST_DTO)).thenReturn(TOKEN_EXPECTED_RESPONSE);

        mockMvc.perform(MockMvcRequestBuilders.post(LOGIN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(MALFORMED_USER)))
                .andExpect(status().isBadRequest());
    }
}
