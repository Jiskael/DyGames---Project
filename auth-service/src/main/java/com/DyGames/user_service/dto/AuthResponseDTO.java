package com.DyGames.user_service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {
    private Long id;
    private String email;
    private String role;
    private boolean active;
    private LocalDateTime createdAt;
}
