package com.DyGames.library_service.dto;

import com.DyGames.library_service.dto.UserRespuesta;
import com.DyGames.library_service.dto.GameRespuesta;
import com.DyGames.library_service.dto.OrderRespuesta;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LibraryRespuesta {

    private Long id;
    private UserRespuesta usuario;
    private GameRespuesta juego;
    private OrderRespuesta orden;
    private LocalDateTime adquiridoEn;
}
