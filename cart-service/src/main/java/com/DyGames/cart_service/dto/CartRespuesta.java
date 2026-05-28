package com.DyGames.cart_service.dto;

import com.DyGames.cart_service.dto.UserRespuesta;
import com.DyGames.cart_service.dto.GameRespuesta;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CartRespuesta {

    private Long id;
    private UserRespuesta usuario;
    private GameRespuesta juego;
    private Double precio;
    private LocalDateTime creadoEn;
}
