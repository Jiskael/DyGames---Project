package com.DyGames.user_service.dto;

import lombok.Data;

@Data
public class AuthRespuesta {

    private Long id;
    private String email;
    private String rol;
    private Boolean activo;
}
