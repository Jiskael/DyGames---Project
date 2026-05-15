package com.DyGames.library_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "biblioteca")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El usuario no puede estar vacio.")
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @NotNull(message = "El juego no puede estar vacio.")
    @Column(name = "juego_id", nullable = false)
    private Long juegoId;

    @Column(name = "orden_id")
    private Long ordenId;

    @Column(name = "adquirido_en", nullable = false)
    private LocalDateTime adquiridoEn = LocalDateTime.now();
}