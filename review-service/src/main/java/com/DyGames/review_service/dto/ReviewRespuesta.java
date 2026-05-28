package com.DyGames.review_service.dto;

import com.DyGames.review_service.dto.UserRespuesta;
import com.DyGames.review_service.dto.GameRespuesta;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReviewRespuesta {

    private Long id;
    private UserRespuesta usuario;
    private GameRespuesta juego;
    private Integer calificacion;
    private String comentario;
    private LocalDateTime creadoEn;
}
