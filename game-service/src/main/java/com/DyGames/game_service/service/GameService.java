package com.DyGames.game_service.service;

import com.DyGames.game_service.dto.GameRespuesta;
import com.DyGames.game_service.mapper.GameMapper;
import com.DyGames.game_service.model.Game;
import com.DyGames.game_service.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameMapper gameMapper;

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Game findById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    public Game save(Game game) {
        return gameRepository.save(game);
    }

    public Game update(Long id, Game game) {
        Game g = gameRepository.findById(id).orElse(null);
        if (g == null) return null;
        g.setTitulo(game.getTitulo());
        g.setDescripcion(game.getDescripcion());
        g.setDesarrollador(game.getDesarrollador());
        g.setPrecio(game.getPrecio());
        g.setFechaLanzamiento(game.getFechaLanzamiento());
        g.setCoverUrl(game.getCoverUrl());
        g.setCategoriaId(game.getCategoriaId());
        g.setActivo(game.getActivo());
        return gameRepository.save(g);
    }

    public void delete(Long id) {
        gameRepository.deleteById(id);
    }

    // Metodos DTO revisar
    public GameRespuesta findDTO(Long id) {
        return gameMapper.toDTO(findById(id));
    }

    public List<GameRespuesta> findDTOList() {
        return gameMapper.toDTOList(findAll());
    }

    // Reportes revisar
    public List<GameRespuesta> findByCategoria(Long categoriaId) {
        return gameMapper.toDTOList(gameRepository.findByCategoriaId(categoriaId));
    }

    public List<GameRespuesta> findActivos() {
        return gameMapper.toDTOList(gameRepository.findByActivoTrue());
    }

    public List<GameRespuesta> findByRangoPrecio(Double min, Double max) {
        return gameMapper.toDTOList(gameRepository.findByPrecioBetween(min, max));
    }

    public List<GameRespuesta> findByDesarrollador(String desarrollador) {
        return gameMapper.toDTOList(gameRepository.findByDesarrollador(desarrollador));
    }

    public List<GameRespuesta> findByTitulo(String titulo) {
        return gameMapper.toDTOList(gameRepository.findByTituloContainingIgnoreCase(titulo));
    }
}