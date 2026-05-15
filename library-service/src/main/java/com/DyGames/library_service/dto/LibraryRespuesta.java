package com.DyGames.library_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LibraryRespuesta {

    private Long id;
    private Long usuarioId;
    private Long juegoId;
    private Long ordenId;
    private LocalDateTime adquiridoEn;
}