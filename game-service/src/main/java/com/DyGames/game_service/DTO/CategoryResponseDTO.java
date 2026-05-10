package com.DyGames.game_service.DTO;

import lombok.*;

/**
 * DTO que representa la respuesta del category-service
 * cuando se consulta una categoría por id via OpenFeign.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDTO {

    private Long id;
    private String name;
    private String description;
    private boolean active;
}
