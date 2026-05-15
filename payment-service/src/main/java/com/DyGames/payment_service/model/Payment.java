package com.DyGames.payment_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La orden no puede estar vacia.")
    @Column(name = "orden_id", nullable = false)
    private Long ordenId;

    @NotNull(message = "El usuario no puede estar vacio.")
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @NotNull(message = "El monto no puede estar vacio.")
    @Column(nullable = false)
    private Double monto;

    @NotBlank(message = "El metodo de pago no puede estar vacio.")
    @Column(nullable = false, length = 50)
    private String metodo;

    @Column(nullable = false, length = 20)
    private String estado = "PENDIENTE";

    @Column(name = "creado_en", nullable = false)
    private LocalDateTime creadoEn = LocalDateTime.now();
}