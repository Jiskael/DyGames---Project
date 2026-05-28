package com.DyGames.review_service.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class GameRespuesta {

    private Long id;
    private String titulo;
    private String desarrollador;
    private Double precio;
    private LocalDate fechaLanzamiento;
    private String coverUrl;
    private Long categoriaId;
}
