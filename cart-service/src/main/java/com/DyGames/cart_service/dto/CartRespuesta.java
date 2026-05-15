package com.DyGames.cart_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CartRespuesta {

    private Long id;
    private Long usuarioId;
    private Long juegoId;
    private Double precio;
    private LocalDateTime creadoEn;
}