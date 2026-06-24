package com.DyGames.category_service.controller;

import com.DyGames.category_service.dto.CategoryRespuesta;
import com.DyGames.category_service.exception.NombreCategoriaExisteException;
import com.DyGames.category_service.model.Category;
import com.DyGames.category_service.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CategoryService categoryService;

    private Category accion;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        accion = new Category(1L, "Accion", "Juegos de accion", "accion.png");
    }

    @Test
    void listar_deberiaRetornar200ConListado() throws Exception {
        when(categoryService.findAll()).thenReturn(List.of(accion));

        mockMvc.perform(get("/api/v1/categorias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Accion"));
    }

    @Test
    void buscarPorId_deberiaRetornar200_siExiste() throws Exception {
        when(categoryService.findById(1L)).thenReturn(accion);

        mockMvc.perform(get("/api/v1/categorias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Accion"));
    }

    @Test
    void buscarPorId_deberiaRetornar404_siNoExiste() throws Exception {
        when(categoryService.findById(99L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/categorias/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void registrar_deberiaRetornar201_siDatosValidos() throws Exception {
        when(categoryService.save(any(Category.class))).thenReturn(accion);

        mockMvc.perform(post("/api/v1/categorias")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(accion)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Accion"));
    }

    @Test
    void registrar_deberiaRetornar400_siNombreVacio() throws Exception {
        Category invalida = new Category(null, "", "desc", "icon.png");

        mockMvc.perform(post("/api/v1/categorias")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(invalida)))
                .andExpect(status().isBadRequest());

        verify(categoryService, never()).save(any());
    }

    @Test
    void registrar_deberiaRetornar400_siNombreYaExiste() throws Exception {
        when(categoryService.save(any(Category.class)))
                .thenThrow(new NombreCategoriaExisteException("El nombre ya existe en el sistema"));

        mockMvc.perform(post("/api/v1/categorias")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(accion)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("El nombre ya existe en el sistema"));
    }

    @Test
    void actualizar_deberiaRetornar200_siExiste() throws Exception {
        when(categoryService.update(eq(1L), any(Category.class))).thenReturn(accion);

        mockMvc.perform(put("/api/v1/categorias/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(accion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Accion"));
    }

    @Test
    void actualizar_deberiaRetornar404_siNoExiste() throws Exception {
        when(categoryService.update(eq(99L), any(Category.class))).thenReturn(null);

        mockMvc.perform(put("/api/v1/categorias/99")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(accion)))
                .andExpect(status().isNotFound());
    }

    @Test
    void eliminar_deberiaRetornar204() throws Exception {
        mockMvc.perform(delete("/api/v1/categorias/1"))
                .andExpect(status().isNoContent());

        verify(categoryService, times(1)).delete(1L);
    }

    @Test
    void listarDTO_deberiaRetornar200() throws Exception {
        CategoryRespuesta dto = new CategoryRespuesta();
        dto.setId(1L);
        dto.setNombre("Accion");
        when(categoryService.findDTOList()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/categorias/listado"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Accion"));
    }

    @Test
    void buscarPorIdDTO_deberiaRetornar404_siNoExiste() throws Exception {
        when(categoryService.findDTO(99L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/categorias/listado/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscarPorNombre_deberiaRetornar200_siExiste() throws Exception {
        CategoryRespuesta dto = new CategoryRespuesta();
        dto.setNombre("Accion");
        when(categoryService.findByNombreDTO("Accion")).thenReturn(dto);

        mockMvc.perform(get("/api/v1/categorias/nombre/Accion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Accion"));
    }

    @Test
    void buscarPorNombre_deberiaRetornar404_siNoExiste() throws Exception {
        when(categoryService.findByNombreDTO("Inexistente")).thenReturn(null);

        mockMvc.perform(get("/api/v1/categorias/nombre/Inexistente"))
                .andExpect(status().isNotFound());
    }
}
