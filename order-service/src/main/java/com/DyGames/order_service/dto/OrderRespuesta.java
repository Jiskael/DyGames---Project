package com.DyGames.order_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderRespuesta {

    private Long id;
    private Long usuarioId;
    private Double total;
    private String estado;
    private LocalDateTime creadoEn;
}