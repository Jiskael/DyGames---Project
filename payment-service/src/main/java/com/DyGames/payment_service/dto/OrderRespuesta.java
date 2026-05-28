package com.DyGames.payment_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderRespuesta {

    private Long id;
    private UserRespuesta usuario;
    private Double total;
    private String estado;
    private LocalDateTime creadoEn;
}
