package com.DyGames.game_service;

import com.DyGames.game_service.dto.GameRespuesta;
import com.DyGames.game_service.mapper.GameMapper;
import com.DyGames.game_service.model.Game;
import com.DyGames.game_service.repository.GameRepository;
import com.DyGames.game_service.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GameMapper gameMapper;

    @InjectMocks
    private GameService gameService;

    private Game juegoActivo;
    private Game juegoInactivo;

    @BeforeEach
    void setUp() {
        juegoActivo = new Game();
        juegoActivo.setId(1L);
        juegoActivo.setTitulo("The Witcher 3");
        juegoActivo.setDescripcion("RPG de mundo abierto");
        juegoActivo.setDesarrolladorId(1L);
        juegoActivo.setPrecio(29.99);
        juegoActivo.setFechaLanzamiento(LocalDate.of(2015, 5, 19));
        juegoActivo.setCategoriaId(1L);
        juegoActivo.setActivo(true);

        juegoInactivo = new Game();
        juegoInactivo.setId(2L);
        juegoInactivo.setTitulo("Juego Descontinuado");
        juegoInactivo.setDesarrolladorId(2L);
        juegoInactivo.setPrecio(9.99);
        juegoInactivo.setCategoriaId(2L);
        juegoInactivo.setActivo(false);
    }

    @Test
    void findAll_debeRetornarListaDeJuegos() {
        // Given
        when(gameRepository.findAll()).thenReturn(List.of(juegoActivo, juegoInactivo));
        // When
        List<Game> resultado = gameService.findAll();
        // Then
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(gameRepository, times(1)).findAll();
    }

    @Test
    void findAll_debeRetornarListaVaciaCuandoNoHayJuegos() {
        // Given
        when(gameRepository.findAll()).thenReturn(List.of());
        // When
        List<Game> resultado = gameService.findAll();
        // Then
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void findById_debeRetornarJuego_cuandoExiste() {
        // Given
        when(gameRepository.findById(1L)).thenReturn(Optional.of(juegoActivo));
        // When
        Game resultado = gameService.findById(1L);
        // Then
        assertNotNull(resultado);
        assertEquals("The Witcher 3", resultado.getTitulo());
        assertEquals(29.99, resultado.getPrecio());
    }

    @Test
    void findById_debeRetornarNull_cuandoNoExiste() {
        // Given
        when(gameRepository.findById(99L)).thenReturn(Optional.empty());
        // When
        Game resultado = gameService.findById(99L);
        // Then
        assertNull(resultado);
    }

    @Test
    void save_debeGuardarYRetornarJuego() {
        // Given
        when(gameRepository.save(any(Game.class))).thenReturn(juegoActivo);
        // When
        Game resultado = gameService.save(juegoActivo);
        // Then
        assertNotNull(resultado);
        assertEquals("The Witcher 3", resultado.getTitulo());
        verify(gameRepository, times(1)).save(juegoActivo);
    }

    @Test
    void update_debeActualizarJuego_cuandoExiste() {
        // Given
        Game datos = new Game();
        datos.setTitulo("The Witcher 3 GOTY");
        datos.setDescripcion("Edicion completa");
        datos.setDesarrolladorId(1L);
        datos.setPrecio(14.99);
        datos.setCategoriaId(1L);
        datos.setActivo(true);

        when(gameRepository.findById(1L)).thenReturn(Optional.of(juegoActivo));
        when(gameRepository.save(any(Game.class))).thenReturn(juegoActivo);
        // When
        Game resultado = gameService.update(1L, datos);
        // Then
        assertNotNull(resultado);
        verify(gameRepository, times(1)).save(any(Game.class));
    }

    @Test
    void update_debeRetornarNull_cuandoNoExiste() {
        // Given
        when(gameRepository.findById(99L)).thenReturn(Optional.empty());
        // When
        Game resultado = gameService.update(99L, juegoActivo);
        // Then
        assertNull(resultado);
        verify(gameRepository, never()).save(any());
    }

    @Test
    void delete_debeEliminarJuego() {
        // Given
        doNothing().when(gameRepository).deleteById(1L);
        // When
        gameService.delete(1L);
        // Then
        verify(gameRepository, times(1)).deleteById(1L);
    }

    @Test
    void findActivos_debeRetornarSoloJuegosActivos() {
        // Given
        GameRespuesta respuesta = new GameRespuesta();
        respuesta.setId(1L);
        respuesta.setTitulo("The Witcher 3");
        when(gameRepository.findByActivoTrue()).thenReturn(List.of(juegoActivo));
        when(gameMapper.toDTOList(List.of(juegoActivo))).thenReturn(List.of(respuesta));
        // When
        List<GameRespuesta> resultado = gameService.findActivos();
        // Then
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(gameRepository, times(1)).findByActivoTrue();
    }

    @Test
    void findByRangoPrecio_debeRetornarJuegosEnRango() {
        // Given
        GameRespuesta respuesta = new GameRespuesta();
        respuesta.setId(1L);
        respuesta.setPrecio(29.99);
        when(gameRepository.findByPrecioBetween(10.0, 50.0)).thenReturn(List.of(juegoActivo));
        when(gameMapper.toDTOList(List.of(juegoActivo))).thenReturn(List.of(respuesta));
        // When
        List<GameRespuesta> resultado = gameService.findByRangoPrecio(10.0, 50.0);
        // Then
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void findByRangoPrecio_debeRetornarVacio_cuandoNingunJuegoEstaEnRango() {
        // Given
        when(gameRepository.findByPrecioBetween(100.0, 200.0)).thenReturn(List.of());
        when(gameMapper.toDTOList(List.of())).thenReturn(List.of());
        // When
        List<GameRespuesta> resultado = gameService.findByRangoPrecio(100.0, 200.0);
        // Then
        assertTrue(resultado.isEmpty());
    }

    @Test
    void findByTitulo_debeRetornarJuegos_cuandoHayCoincidencia() {
        // Given
        GameRespuesta respuesta = new GameRespuesta();
        respuesta.setTitulo("The Witcher 3");
        when(gameRepository.findByTituloContainingIgnoreCase("witcher")).thenReturn(List.of(juegoActivo));
        when(gameMapper.toDTOList(List.of(juegoActivo))).thenReturn(List.of(respuesta));
        // When
        List<GameRespuesta> resultado = gameService.findByTitulo("witcher");
        // Then
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    void findByCategoria_debeRetornarJuegosDeCategoria() {
        // Given
        GameRespuesta respuesta = new GameRespuesta();
        respuesta.setId(1L);
        when(gameRepository.findByCategoriaId(1L)).thenReturn(List.of(juegoActivo));
        when(gameMapper.toDTOList(List.of(juegoActivo))).thenReturn(List.of(respuesta));
        // When
        List<GameRespuesta> resultado = gameService.findByCategoria(1L);
        // Then
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(gameRepository, times(1)).findByCategoriaId(1L);
    }
}
