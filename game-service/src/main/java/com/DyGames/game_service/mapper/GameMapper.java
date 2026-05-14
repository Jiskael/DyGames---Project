package com.DyGames.game_service.mapper;

import com.DyGames.game_service.dto.GameRespuesta;
import com.DyGames.game_service.model.Game;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GameMapper {

    public GameRespuesta toDTO(Game game) {
        if (game == null) return null;
        GameRespuesta gr = new GameRespuesta();
        gr.setId(game.getId());
        gr.setTitulo(game.getTitulo());
        gr.setDesarrollador(game.getDesarrollador());
        gr.setPrecio(game.getPrecio());
        gr.setFechaLanzamiento(game.getFechaLanzamiento());
        gr.setCoverUrl(game.getCoverUrl());
        gr.setCategoriaId(game.getCategoriaId());
        return gr;
    }

    public List<GameRespuesta> toDTOList(List<Game> lista) {
        return lista.stream().map(this::toDTO).toList();
    }
}