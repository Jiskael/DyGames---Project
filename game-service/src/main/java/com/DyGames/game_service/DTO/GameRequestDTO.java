package com.DyGames.game_service.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameRequestDTO {

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 200, message = "El título no puede superar 200 caracteres")
    private String title;

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    @NotBlank(message = "El desarrollador es obligatorio")
    @Size(max = 150, message = "El desarrollador no puede superar 150 caracteres")
    private String developer;

    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private double price;

    @NotBlank(message = "La fecha de lanzamiento es obligatoria")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "La fecha debe tener formato YYYY-MM-DD")
    private String releaseDate;

    @Size(max = 500, message = "La URL de portada no puede superar 500 caracteres")
    private String coverUrl;

    @NotNull(message = "El categoryId es obligatorio")
    @Positive(message = "El categoryId debe ser un número positivo")
    private Long categoryId;

    private boolean active = true;
}
