package com.DyGames.category_service.service;

import com.DyGames.category_service.dto.CategoryRespuesta;
import com.DyGames.category_service.exception.NombreCategoriaExisteException;
import com.DyGames.category_service.mapper.CategoryMapper;
import com.DyGames.category_service.model.Category;
import com.DyGames.category_service.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    private Category accion;
    private Category aventura;

    @BeforeEach
    void setUp() {
        accion = new Category(1L, "Accion", "Juegos de accion", "accion.png");
        aventura = new Category(2L, "Aventura", "Juegos de aventura", "aventura.png");
    }

    // ───────────────────────── findAll ─────────────────────────

    @Test
    void findAll_deberiaRetornarTodasLasCategorias() {
        when(categoryRepository.findAll()).thenReturn(List.of(accion, aventura));

        List<Category> resultado = categoryService.findAll();

        assertThat(resultado).hasSize(2).containsExactly(accion, aventura);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void findAll_deberiaRetornarListaVacia_siNoHayCategorias() {
        when(categoryRepository.findAll()).thenReturn(List.of());

        List<Category> resultado = categoryService.findAll();

        assertThat(resultado).isEmpty();
    }

    // ───────────────────────── findById ─────────────────────────

    @Test
    void findById_deberiaRetornarCategoria_siExiste() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(accion));

        Category resultado = categoryService.findById(1L);

        assertThat(resultado).isEqualTo(accion);
    }

    @Test
    void findById_deberiaRetornarNull_siNoExiste() {
        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());

        Category resultado = categoryService.findById(99L);

        assertNull(resultado);
    }

    // ───────────────────────── save ─────────────────────────

    @Test
    void save_deberiaGuardarCategoria_siElNombreNoExiste() {
        when(categoryRepository.existsByNombre("Accion")).thenReturn(false);
        when(categoryRepository.save(accion)).thenReturn(accion);

        Category resultado = categoryService.save(accion);

        assertThat(resultado).isEqualTo(accion);
        verify(categoryRepository, times(1)).save(accion);
    }

    @Test
    void save_deberiaLanzarExcepcion_siElNombreYaExiste() {
        when(categoryRepository.existsByNombre("Accion")).thenReturn(true);

        assertThrows(NombreCategoriaExisteException.class, () -> categoryService.save(accion));

        verify(categoryRepository, never()).save(any());
    }

    // ───────────────────────── update ─────────────────────────

    @Test
    void update_deberiaActualizarCategoria_siExiste() {
        Category cambios = new Category(null, "Accion RPG", "Nueva desc", "nuevo.png");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(accion));
        when(categoryRepository.save(any(Category.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Category resultado = categoryService.update(1L, cambios);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNombre()).isEqualTo("Accion RPG");
        assertThat(resultado.getDescripcion()).isEqualTo("Nueva desc");
        assertThat(resultado.getIconUrl()).isEqualTo("nuevo.png");
        verify(categoryRepository, times(1)).save(accion);
    }

    @Test
    void update_deberiaRetornarNull_siNoExiste() {
        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());

        Category resultado = categoryService.update(99L, accion);

        assertNull(resultado);
        verify(categoryRepository, never()).save(any());
    }

    // ───────────────────────── delete ─────────────────────────

    @Test
    void delete_deberiaInvocarRepositorio() {
        categoryService.delete(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }

    // ───────────────────────── findDTO ─────────────────────────

    @Test
    void findDTO_deberiaRetornarDTO_siExiste() {
        CategoryRespuesta dto = new CategoryRespuesta();
        dto.setId(1L);
        dto.setNombre("Accion");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(accion));
        when(categoryMapper.toDTO(accion)).thenReturn(dto);

        CategoryRespuesta resultado = categoryService.findDTO(1L);

        assertThat(resultado.getNombre()).isEqualTo("Accion");
    }

    @Test
    void findDTO_deberiaRetornarNull_siNoExiste() {
        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());
        when(categoryMapper.toDTO(null)).thenReturn(null);

        CategoryRespuesta resultado = categoryService.findDTO(99L);

        assertNull(resultado);
    }

    // ───────────────────────── findDTOList ─────────────────────────

    @Test
    void findDTOList_deberiaRetornarListaDeDTOs() {
        CategoryRespuesta dto1 = new CategoryRespuesta();
        dto1.setId(1L);
        CategoryRespuesta dto2 = new CategoryRespuesta();
        dto2.setId(2L);

        when(categoryRepository.findAll()).thenReturn(List.of(accion, aventura));
        when(categoryMapper.toDTOList(List.of(accion, aventura))).thenReturn(List.of(dto1, dto2));

        List<CategoryRespuesta> resultado = categoryService.findDTOList();

        assertThat(resultado).hasSize(2);
    }

    // ───────────────────────── findByNombreDTO ─────────────────────────

    @Test
    void findByNombreDTO_deberiaRetornarDTO_siExiste() {
        CategoryRespuesta dto = new CategoryRespuesta();
        dto.setNombre("Accion");

        when(categoryRepository.findByNombre("Accion")).thenReturn(Optional.of(accion));
        when(categoryMapper.toDTO(accion)).thenReturn(dto);

        CategoryRespuesta resultado = categoryService.findByNombreDTO("Accion");

        assertThat(resultado.getNombre()).isEqualTo("Accion");
    }

    @Test
    void findByNombreDTO_deberiaRetornarNull_siNoExiste() {
        when(categoryRepository.findByNombre("Inexistente")).thenReturn(Optional.empty());
        when(categoryMapper.toDTO(null)).thenReturn(null);

        CategoryRespuesta resultado = categoryService.findByNombreDTO("Inexistente");

        assertNull(resultado);
    }
}
