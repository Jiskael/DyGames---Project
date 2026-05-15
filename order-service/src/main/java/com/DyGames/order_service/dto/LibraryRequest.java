package com.DyGames.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryRequest {

    private Long usuarioId;
    private Long juegoId;
    private Long ordenId;
}