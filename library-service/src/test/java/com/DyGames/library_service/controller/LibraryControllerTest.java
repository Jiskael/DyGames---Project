package com.DyGames.library_service.controller;

import com.DyGames.library_service.dto.LibraryRespuesta;
import com.DyGames.library_service.exception.JuegoYaEnBibliotecaException;
import com.DyGames.library_service.model.Library;
import com.DyGames.library_service.service.LibraryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LibraryController.class)
class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private LibraryService libraryService;

    private Library registro;

    @BeforeEach
    void setUp() {
        registro = new Library(1L, 10L, 100L, 1000L, LocalDateTime.of(2026, 1, 1, 12, 0));
    }

    @Test
    void listar_deberiaRetornar200ConListado() throws Exception {
        when(libraryService.findAll()).thenReturn(List.of(registro));

        mockMvc.perform(get("/api/v1/biblioteca"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].usuarioId").value(10));
    }

    @Test
    void buscarPorId_deberiaRetornar200_siExiste() throws Exception {
        when(libraryService.findById(1L)).thenReturn(registro);

        mockMvc.perform(get("/api/v1/biblioteca/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.juegoId").value(100));
    }

    @Test
    void buscarPorId_deberiaRetornar404_siNoExiste() throws Exception {
        when(libraryService.findById(99L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/biblioteca/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void registrar_deberiaRetornar201_siDatosValidos() throws Exception {
        when(libraryService.save(any(Library.class))).thenReturn(registro);

        mockMvc.perform(post("/api/v1/biblioteca")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registro)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.usuarioId").value(10));
    }

    @Test
    void registrar_deberiaRetornar400_siUsuarioOJuegoNulos() throws Exception {
        Library invalido = new Library(null, null, null, null, LocalDateTime.now());

        mockMvc.perform(post("/api/v1/biblioteca")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(invalido)))
                .andExpect(status().isBadRequest());

        verify(libraryService, never()).save(any());
    }

    @Test
    void registrar_deberiaRetornar400_siJuegoYaEnBiblioteca() throws Exception {
        when(libraryService.save(any(Library.class)))
                .thenThrow(new JuegoYaEnBibliotecaException("El usuario ya tiene este juego en su biblioteca"));

        mockMvc.perform(post("/api/v1/biblioteca")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(registro)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("El usuario ya tiene este juego en su biblioteca"));
    }

    @Test
    void eliminar_deberiaRetornar204() throws Exception {
        mockMvc.perform(delete("/api/v1/biblioteca/1"))
                .andExpect(status().isNoContent());

        verify(libraryService, times(1)).delete(1L);
    }

    @Test
    void listarDTO_deberiaRetornar200() throws Exception {
        LibraryRespuesta dto = new LibraryRespuesta();
        dto.setId(1L);
        when(libraryService.findDTOList()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/biblioteca/listado"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void buscarPorIdDTO_deberiaRetornar404_siNoExiste() throws Exception {
        when(libraryService.findDTO(99L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/biblioteca/listado/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscarPorUsuario_deberiaRetornar200() throws Exception {
        LibraryRespuesta dto = new LibraryRespuesta();
        dto.setId(1L);
        when(libraryService.findByUsuario(10L)).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/biblioteca/usuario/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void buscarPorJuego_deberiaRetornar200() throws Exception {
        LibraryRespuesta dto = new LibraryRespuesta();
        dto.setId(1L);
        when(libraryService.findByJuego(100L)).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/biblioteca/juego/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void verificarJuego_deberiaRetornarTrue() throws Exception {
        when(libraryService.tieneJuego(10L, 100L)).thenReturn(true);

        mockMvc.perform(get("/api/v1/biblioteca/verificar")
                        .param("usuarioId", "10")
                        .param("juegoId", "100"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void verificarJuego_deberiaRetornarFalse() throws Exception {
        when(libraryService.tieneJuego(10L, 999L)).thenReturn(false);

        mockMvc.perform(get("/api/v1/biblioteca/verificar")
                        .param("usuarioId", "10")
                        .param("juegoId", "999"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}
