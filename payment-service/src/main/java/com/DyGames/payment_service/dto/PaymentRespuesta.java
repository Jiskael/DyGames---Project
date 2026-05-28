package com.DyGames.payment_service.dto;

import com.DyGames.payment_service.dto.OrderRespuesta;
import com.DyGames.payment_service.dto.UserRespuesta;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PaymentRespuesta {

    private Long id;
    private OrderRespuesta orden;
    private UserRespuesta usuario;
    private Double monto;
    private String metodo;
    private String estado;
    private LocalDateTime creadoEn;
}
