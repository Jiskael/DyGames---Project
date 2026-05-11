package com.DyGames.category_service.dto;

import lombok.Data;

@Data
public class CategoryRespuesta {

    private Long id;
    private String nombre;
    private String descripcion;
    private String iconUrl;
}
