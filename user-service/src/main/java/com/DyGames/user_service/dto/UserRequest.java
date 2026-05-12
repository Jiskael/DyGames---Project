package com.DyGames.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "El username no puede estar vacio.")
    private String username;

    @Email(message = "Formato de email invalido")
    @NotBlank(message = "El email no puede estar vacio.")
    private String email;

    private String nombre;
    private String pfpUrl;
    private Long authId;
}
