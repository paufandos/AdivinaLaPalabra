package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services;

import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.AuthDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.LoginDTO;

public interface IUserService {

    public AuthDTO validateUser(LoginDTO user);
    
}
