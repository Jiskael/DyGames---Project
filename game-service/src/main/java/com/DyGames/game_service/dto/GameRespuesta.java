package com.DyGames.game_service.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class GameRespuesta {

    private Long id;
    private String titulo;
    private DeveloperRespuesta desarrollador;
    private Double precio;
    private LocalDate fechaLanzamiento;
    private String coverUrl;
    private CategoryRespuesta categoria;
}
