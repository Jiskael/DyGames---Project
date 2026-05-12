package com.DyGames.user_service.dto;

import lombok.Data;

@Data
public class UserRespuesta {

    private Long id;
    private String username;
    private String email;
    private String nombre;
    private String pfpUrl;
}
