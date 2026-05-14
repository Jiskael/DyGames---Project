package com.DyGames.user_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "auth_id", nullable = false)
    private Long authId;

    @NotBlank(message = "El username no puede estar vacio.")
    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(length = 30)
    private String nombre;

    @Column(name = "pfp_url")
    private String pfpUrl;

    @Email(message = "Formato de email invalido.")
    @Column(length = 20)
    private String email;

    @Column(name = "creado_en", nullable = false)
    private LocalDateTime creadoEn = LocalDateTime.now();
}
