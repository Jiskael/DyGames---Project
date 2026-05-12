package com.DyGames.user_service.service;

import com.DyGames.user_service.dto.UserRequest;
import com.DyGames.user_service.dto.UserRespuesta;
import com.DyGames.user_service.model.User;
import com.DyGames.user_service.repository.UserRepository;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Metodo crear usuario
    public UserRespuesta crearUsuario(UserRequest r) {
        if (userRepository.existsByUsername(r.getUsername())) {
            throw new RuntimeException("El username ya existe");
        }
        User usuario = new User();
        usuario.setUsername(r.getUsername());
        usuario.setEmail(r.getEmail());
        usuario.setNombre(r.getNombre());
        usuario.setPfpUrl(r.getPfpUrl());
        usuario.setAuthId(r.getAuthId());

        User user = userRepository.save(usuario);
        return DTO(user);

    }

    //Copy paste de convertir modelo a DTO
    private UserRespuesta DTO(User user) {
        UserRespuesta respuesta = new UserRespuesta();
        respuesta.setUsername(user.getUsername());
        respuesta.setEmail(user.getEmail());
        respuesta.setPfpUrl(user.getPfpUrl());
        respuesta.setNombre(user.getNombre());
        respuesta.setId(user.getId());
        return respuesta;
        //11-05-26
    }
}
