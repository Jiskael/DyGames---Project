package com.DyGames.user_service.dto;

import com.DyGames.user_service.dto.AuthRespuesta;
import lombok.Data;

@Data
public class UserRespuesta {

    private Long id;
    private AuthRespuesta auth;
    private String username;
    private String email;
    private String nombre;
    private String pfpUrl;
}
