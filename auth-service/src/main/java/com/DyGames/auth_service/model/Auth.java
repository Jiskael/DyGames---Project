package com.DyGames.auth_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "auth")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Formato de email invalido.")
    @NotBlank(message = "El email no puede estar vacio.")
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank(message = "La contrasena no puede estar vacia.")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String rol = "USER";

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(comment = "creado_en", nullable = false)
    private LocalDateTime creadoEn = LocalDateTime.now();
}