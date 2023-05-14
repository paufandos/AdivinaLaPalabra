package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers;

import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.AuthDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.LoginDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.entities.User;

public class AuthHelper {

    public static final String LOGIN_URL = "/auth/login";

    public static final String AUTH_HEADER = "Authorization";

    public static final String AUTH_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiY0dGMVptRnVaRzl6IiwiaWF0IjoxNjgzNTM3NDU5LCJleHAiOjI2ODM1Mzc0NTh9.1wcPPYvUA5e6FCsPvfjp073ioL_kY4plPNykmFmGvCs";
    
    public static final String AUTH_TOKEN_HEADER = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiY0dGMVptRnVaRzl6IiwiaWF0IjoxNjgzNTM3NDU5LCJleHAiOjI2ODM1Mzc0NTh9.1wcPPYvUA5e6FCsPvfjp073ioL_kY4plPNykmFmGvCs";

    public static final String BASE64_USERNAME = "cGF1ZmFuZG9z";

    public static final String BASE64_PASSWORD = "MTIzNDU2";

    public static final User USER = new User(BASE64_USERNAME,BASE64_PASSWORD);

    public static final LoginDTO USER_REQUEST_DTO = new LoginDTO(BASE64_USERNAME, BASE64_PASSWORD);

    public static final LoginDTO USER_REQUEST_BODY = new LoginDTO("Y0dGMVptRnVaRzl6", "TVRJek5EVTI=");
    
    public static final User DEFAULT_USER = new User(BASE64_USERNAME, BASE64_PASSWORD);

    public static final String DEFAULT_USERNAME = "paufandos";

    public static final AuthDTO TOKEN_EXPECTED_RESPONSE = new AuthDTO(AUTH_TOKEN);
}