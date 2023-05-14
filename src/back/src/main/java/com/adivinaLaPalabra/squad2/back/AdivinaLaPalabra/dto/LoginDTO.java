package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto;

import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.utilities.Base64Utils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 4, message = "El nombre de usuario debe tener al menos 4 caracteres")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "El nombre de usuario no puede contener caracteres especiales")
    private String name;

    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @Size(max = 12, message = "La contraseña debe tener un máximo de 12 caracteres")
    private String password;

    public LoginDTO(String name, String password) {
        this.name = Base64Utils.decode(name);
        this.password = Base64Utils.decode(password);
    }
}
