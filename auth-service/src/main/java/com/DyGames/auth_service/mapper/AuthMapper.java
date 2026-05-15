package com.DyGames.auth_service.mapper;

import com.DyGames.auth_service.dto.AuthRespuesta;
import com.DyGames.auth_service.model.Auth;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AuthMapper {

    public AuthRespuesta toDTO(Auth auth) {
        if (auth == null) return null;
        AuthRespuesta ar = new AuthRespuesta();
        ar.setId(auth.getId());
        ar.setEmail(auth.getEmail());
        ar.setRol(auth.getRol());
        ar.setActivo(auth.getActivo());
        return ar;
    }

    public List<AuthRespuesta> toDTOList(List<Auth> lista) {
        return lista.stream().map(this::toDTO).toList();
    }
}