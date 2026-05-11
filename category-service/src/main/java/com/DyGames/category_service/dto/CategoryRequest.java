package com.DyGames.category_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank(message = "El nombre no puede estar vacio.")
    private String nombre;

    private String descripcion;
    private String iconUrl;
}
