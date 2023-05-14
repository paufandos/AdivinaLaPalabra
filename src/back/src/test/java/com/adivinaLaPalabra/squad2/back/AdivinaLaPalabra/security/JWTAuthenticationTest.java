package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.security;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.security.jwt.JwtTokenFilter;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.security.jwt.JwtUtils;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services.impl.UserDetailsServiceImpl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class JWTAuthenticationTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private JwtTokenFilter jwtTokenFilter;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void testEndPointThatRequiresTokenWithoutTokenMustRetunrUnauthorizedStatus() throws Exception {
        final String URL_NOT_ALLOWED = "/newGame";
        mockMvc.perform(MockMvcRequestBuilders.get(URL_NOT_ALLOWED))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void doFilterInternalMustVaidatePassedToken() throws Exception {
        // TO DO
    }

    @Test
    public void validUserLoginMustReturnAuthToken() throws Exception {
        // TO DO
    }

    @Test
    public void invalidUserLoginMustReturnUnauthorizedStatus() throws Exception {
        // TO DO
    }
}