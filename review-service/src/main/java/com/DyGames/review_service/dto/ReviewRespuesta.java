package com.DyGames.review_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReviewRespuesta {

    private Long id;
    private Long usuarioId;
    private Long juegoId;
    private Integer calificacion;
    private String comentario;
    private LocalDateTime creadoEn;
}