package com.DyGames.user_service.mapper;

import com.DyGames.user_service.dto.UserRespuesta;
import com.DyGames.user_service.model.User;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserMapper {

    public UserRespuesta toDTO(User user) {
        if (user == null) return null;
        UserRespuesta r = new UserRespuesta();
        r.setId(user.getId());
        r.setUsername(user.getUsername());
        r.setEmail(user.getEmail());
        r.setNombre(user.getNombre());
        r.setPfpUrl(user.getPfpUrl());
        return r;
    }

    public List<UserRespuesta> toDTOList(List<User> lista) {
        return lista.stream().map(this::toDTO).toList();
    }
}
