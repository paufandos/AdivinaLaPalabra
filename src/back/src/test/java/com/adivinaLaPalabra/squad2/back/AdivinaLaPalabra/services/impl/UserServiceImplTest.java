package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services.impl;

import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.AuthHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.AuthDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.security.jwt.JwtUtils;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    JwtUtils jwtUtils;

    @Mock
    AuthenticationManager authenticationManager;


    @Test
    public void testValidateUserMustReturnAuthDTO() {
        final Authentication AUTHENTICATION =  new UsernamePasswordAuthenticationToken(USER_REQUEST_DTO.getName(), USER_REQUEST_DTO.getPassword());

        when(authenticationManager.authenticate(AUTHENTICATION)).thenReturn(AUTHENTICATION);
        SecurityContextHolder.getContext().setAuthentication(AUTHENTICATION);
        when(jwtUtils.generateJwtToken(AUTHENTICATION)).thenReturn(TOKEN_EXPECTED_RESPONSE.token());

        AuthDTO responsed_dto = userServiceImpl.validateUser(USER_REQUEST_DTO);
        assertEquals(TOKEN_EXPECTED_RESPONSE,responsed_dto);

    }
}
