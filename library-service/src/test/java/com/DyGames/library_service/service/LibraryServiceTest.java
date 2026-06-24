package com.DyGames.library_service.service;

import com.DyGames.library_service.dto.LibraryRespuesta;
import com.DyGames.library_service.exception.JuegoYaEnBibliotecaException;
import com.DyGames.library_service.mapper.LibraryMapper;
import com.DyGames.library_service.model.Library;
import com.DyGames.library_service.repository.LibraryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibraryServiceTest {

    @Mock
    private LibraryRepository libraryRepository;

    @Mock
    private LibraryMapper libraryMapper;

    @InjectMocks
    private LibraryService libraryService;

    private Library registro;

    @BeforeEach
    void setUp() {
        registro = new Library(1L, 10L, 100L, 1000L, LocalDateTime.of(2026, 1, 1, 12, 0));
    }

    // ───────────────────────── findAll ─────────────────────────

    @Test
    void findAll_deberiaRetornarTodosLosRegistros() {
        when(libraryRepository.findAll()).thenReturn(List.of(registro));

        List<Library> resultado = libraryService.findAll();

        assertThat(resultado).hasSize(1).containsExactly(registro);
    }

    // ───────────────────────── findById ─────────────────────────

    @Test
    void findById_deberiaRetornarRegistro_siExiste() {
        when(libraryRepository.findById(1L)).thenReturn(Optional.of(registro));

        Library resultado = libraryService.findById(1L);

        assertThat(resultado).isEqualTo(registro);
    }

    @Test
    void findById_deberiaRetornarNull_siNoExiste() {
        when(libraryRepository.findById(99L)).thenReturn(Optional.empty());

        Library resultado = libraryService.findById(99L);

        assertNull(resultado);
    }

    // ───────────────────────── save ─────────────────────────

    @Test
    void save_deberiaGuardarRegistro_siNoEstaDuplicado() {
        when(libraryRepository.existsByUsuarioIdAndJuegoId(10L, 100L)).thenReturn(false);
        when(libraryRepository.save(registro)).thenReturn(registro);

        Library resultado = libraryService.save(registro);

        assertThat(resultado).isEqualTo(registro);
        verify(libraryRepository, times(1)).save(registro);
    }

    @Test
    void save_deberiaLanzarExcepcion_siJuegoYaEstaEnBiblioteca() {
        when(libraryRepository.existsByUsuarioIdAndJuegoId(10L, 100L)).thenReturn(true);

        assertThrows(JuegoYaEnBibliotecaException.class, () -> libraryService.save(registro));

        verify(libraryRepository, never()).save(any());
    }

    // ───────────────────────── delete ─────────────────────────

    @Test
    void delete_deberiaInvocarRepositorio() {
        libraryService.delete(1L);

        verify(libraryRepository, times(1)).deleteById(1L);
    }

    // ───────────────────────── findDTO ─────────────────────────

    @Test
    void findDTO_deberiaRetornarDTO_siExiste() {
        LibraryRespuesta dto = new LibraryRespuesta();
        dto.setId(1L);

        when(libraryRepository.findById(1L)).thenReturn(Optional.of(registro));
        when(libraryMapper.toDTO(registro)).thenReturn(dto);

        LibraryRespuesta resultado = libraryService.findDTO(1L);

        assertThat(resultado.getId()).isEqualTo(1L);
    }

    @Test
    void findDTO_deberiaRetornarNull_siNoExiste() {
        when(libraryRepository.findById(99L)).thenReturn(Optional.empty());
        when(libraryMapper.toDTO(null)).thenReturn(null);

        LibraryRespuesta resultado = libraryService.findDTO(99L);

        assertNull(resultado);
    }

    // ───────────────────────── findDTOList ─────────────────────────

    @Test
    void findDTOList_deberiaRetornarListaDeDTOs() {
        LibraryRespuesta dto = new LibraryRespuesta();
        dto.setId(1L);

        when(libraryRepository.findAll()).thenReturn(List.of(registro));
        when(libraryMapper.toDTOList(List.of(registro))).thenReturn(List.of(dto));

        List<LibraryRespuesta> resultado = libraryService.findDTOList();

        assertThat(resultado).hasSize(1);
    }

    // ───────────────────────── findByUsuario ─────────────────────────

    @Test
    void findByUsuario_deberiaRetornarRegistrosDelUsuario() {
        LibraryRespuesta dto = new LibraryRespuesta();
        dto.setId(1L);

        when(libraryRepository.findByUsuarioId(10L)).thenReturn(List.of(registro));
        when(libraryMapper.toDTOList(List.of(registro))).thenReturn(List.of(dto));

        List<LibraryRespuesta> resultado = libraryService.findByUsuario(10L);

        assertThat(resultado).hasSize(1);
        verify(libraryRepository, times(1)).findByUsuarioId(10L);
    }

    @Test
    void findByUsuario_deberiaRetornarListaVacia_siNoTieneJuegos() {
        when(libraryRepository.findByUsuarioId(20L)).thenReturn(List.of());
        when(libraryMapper.toDTOList(List.of())).thenReturn(List.of());

        List<LibraryRespuesta> resultado = libraryService.findByUsuario(20L);

        assertThat(resultado).isEmpty();
    }

    // ───────────────────────── findByJuego ─────────────────────────

    @Test
    void findByJuego_deberiaRetornarRegistrosDelJuego() {
        LibraryRespuesta dto = new LibraryRespuesta();
        dto.setId(1L);

        when(libraryRepository.findByJuegoId(100L)).thenReturn(List.of(registro));
        when(libraryMapper.toDTOList(List.of(registro))).thenReturn(List.of(dto));

        List<LibraryRespuesta> resultado = libraryService.findByJuego(100L);

        assertThat(resultado).hasSize(1);
    }

    // ───────────────────────── tieneJuego ─────────────────────────

    @Test
    void tieneJuego_deberiaRetornarTrue_siExisteCombinacion() {
        when(libraryRepository.existsByUsuarioIdAndJuegoId(10L, 100L)).thenReturn(true);

        assertTrue(libraryService.tieneJuego(10L, 100L));
    }

    @Test
    void tieneJuego_deberiaRetornarFalse_siNoExisteCombinacion() {
        when(libraryRepository.existsByUsuarioIdAndJuegoId(10L, 999L)).thenReturn(false);

        assertFalse(libraryService.tieneJuego(10L, 999L));
    }
}
