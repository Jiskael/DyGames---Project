package com.DyGames.game_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "juegos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El titulo no puede estar vacio.")
    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @NotNull(message = "El desarrollador no puede estar vacio.")
    @Column(name = "desarrollador_id", nullable = false)
    private Long desarrolladorId;

    @NotNull(message = "El precio no puede estar vacio.")
    @Column(nullable = false)
    private Double precio;

    @Column(name = "fecha_lanzamiento")
    private LocalDate fechaLanzamiento;

    @Column(name = "cover_url")
    private String coverUrl;

    @NotNull(message = "La categoria no puede estar vacia.")
    @Column(name = "categoria_id", nullable = false)
    private Long categoriaId;

    @Column(nullable = false)
    private Boolean activo = true;
}
