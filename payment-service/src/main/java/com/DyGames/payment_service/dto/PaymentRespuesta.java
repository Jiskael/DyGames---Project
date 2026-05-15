package com.DyGames.payment_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PaymentRespuesta {

    private Long id;
    private Long ordenId;
    private Long usuarioId;
    private Double monto;
    private String metodo;
    private String estado;
    private LocalDateTime creadoEn;
}