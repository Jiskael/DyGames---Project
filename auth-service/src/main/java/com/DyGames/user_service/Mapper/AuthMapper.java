package com.DyGames.user_service.Mapper;

import com.DyGames.user_service.dto.AuthRequestDTO;
import com.DyGames.user_service.Model.Auth;
import com.DyGames.user_service.dto.AuthResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
    /*Supuestamente combierte Auth en entidad
    Contraseña debe ser sifrada primero antes que inicie el metodo*/
    public Auth toEntity(AuthRequestDTO dto, String encodedPassword) {
        return Auth.builder()
                .email(dto.getEmail())
                .password(encodedPassword)
                .role(dto.getRole())
                .active(dto.isActive())
                .build();
    }
    /*Supuestamente combierte Auth en entidad
    * la constrasela NO expone en respuesta*/
    public AuthResponseDTO toResponseDTO(Auth auth) {
        return AuthResponseDTO.builder()
                .id(auth.getId())
                .email(auth.getEmail())
                .role(auth.getRole())
                .active(auth.isActive())
                .createdAt(auth.getCreatedAt())
                .build();
    }
    /*Actualiza campos existenes con datos del DTO*/
    public void updateEntityFromDTO(AuthRequestDTO dto, String encodedPassword, Auth auth) {
        auth.setEmail(dto.getEmail());
        auth.setPassword(encodedPassword);
        auth.setRole(dto.getRole());
        auth.setActive(dto.isActive());
    }
}
