package com.DyGames.user_service;

import com.DyGames.user_service.dto.UserRespuesta;
import com.DyGames.user_service.exception.UsernameExisteException;
import com.DyGames.user_service.mapper.UserMapper;
import com.DyGames.user_service.model.User;
import com.DyGames.user_service.repository.UserRepository;
import com.DyGames.user_service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User usuario;
    private UserRespuesta usuarioRespuesta;

    @BeforeEach
    void setUp() {
        usuario = new User();
        usuario.setId(1L);
        usuario.setAuthId(10L);
        usuario.setUsername("gamer123");
        usuario.setNombre("Juan Pérez");
        usuario.setEmail("juan@dygames.cl");
        usuario.setPfpUrl("https://cdn.dygames.cl/avatars/1.png");
        usuario.setCreadoEn(LocalDateTime.now());

        usuarioRespuesta = new UserRespuesta();
        usuarioRespuesta.setId(1L);
        usuarioRespuesta.setUsername("gamer123");
        usuarioRespuesta.setEmail("juan@dygames.cl");
        usuarioRespuesta.setNombre("Juan Pérez");
    }

    @Test
    void findAll_debeRetornarListaDeUsuarios() {
        // Given
        when(userRepository.findAll()).thenReturn(List.of(usuario));
        // When
        List<User> resultado = userService.findAll();
        // Then
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void findAll_debeRetornarListaVaciaCuandoNoHayUsuarios() {
        // Given
        when(userRepository.findAll()).thenReturn(List.of());
        // When
        List<User> resultado = userService.findAll();
        // Then
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void findById_debeRetornarUsuario_cuandoExiste() {
        // Given
        when(userRepository.findById(1L)).thenReturn(Optional.of(usuario));
        // When
        User resultado = userService.findById(1L);
        // Then
        assertNotNull(resultado);
        assertEquals("gamer123", resultado.getUsername());
        assertEquals("juan@dygames.cl", resultado.getEmail());
    }

    @Test
    void findById_debeRetornarNull_cuandoNoExiste() {
        // Given
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        // When
        User resultado = userService.findById(99L);
        // Then
        assertNull(resultado);
    }

    @Test
    void save_debeGuardarUsuario_cuandoUsernameEsNuevo() {
        // Given
        when(userRepository.existsByUsername("gamer123")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(usuario);
        // When
        User resultado = userService.save(usuario);
        // Then
        assertNotNull(resultado);
        assertEquals("gamer123", resultado.getUsername());
        verify(userRepository, times(1)).save(usuario);
    }

    @Test
    void save_debeLanzarExcepcion_cuandoUsernameYaExiste() {
        // Given - username duplicado: regla de negocio critica
        when(userRepository.existsByUsername("gamer123")).thenReturn(true);
        // When / Then
        assertThrows(UsernameExisteException.class, () -> userService.save(usuario));
        verify(userRepository, never()).save(any());
    }

    @Test
    void update_debeActualizarUsuario_cuandoExiste() {
        // Given
        User datos = new User();
        datos.setUsername("gamer_pro");
        datos.setEmail("pro@dygames.cl");
        datos.setNombre("Juan Pro");
        datos.setPfpUrl("https://cdn.dygames.cl/avatars/pro.png");

        when(userRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(userRepository.save(any(User.class))).thenReturn(usuario);
        // When
        User resultado = userService.update(1L, datos);
        // Then
        assertNotNull(resultado);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void update_debeRetornarNull_cuandoUsuarioNoExiste() {
        // Given
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        // When
        User resultado = userService.update(99L, usuario);
        // Then
        assertNull(resultado);
        verify(userRepository, never()).save(any());
    }

    @Test
    void delete_debeEliminarUsuario() {
        // Given
        doNothing().when(userRepository).deleteById(1L);
        // When
        userService.delete(1L);
        // Then
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void findByUsernameDTO_debeRetornarDTO_cuandoUsernameExiste() {
        // Given
        when(userRepository.findByUsername("gamer123")).thenReturn(Optional.of(usuario));
        when(userMapper.toDTO(usuario)).thenReturn(usuarioRespuesta);
        // When
        UserRespuesta resultado = userService.findByUsernameDTO("gamer123");
        // Then
        assertNotNull(resultado);
        assertEquals("gamer123", resultado.getUsername());
    }

    @Test
    void findByUsernameDTO_debeRetornarNull_cuandoNoExiste() {
        // Given
        when(userRepository.findByUsername("fantasma")).thenReturn(Optional.empty());
        when(userMapper.toDTO(null)).thenReturn(null);
        // When
        UserRespuesta resultado = userService.findByUsernameDTO("fantasma");
        // Then
        assertNull(resultado);
    }

    @Test
    void findByEmailDTO_debeRetornarDTO_cuandoEmailExiste() {
        // Given
        when(userRepository.findByEmail("juan@dygames.cl")).thenReturn(Optional.of(usuario));
        when(userMapper.toDTO(usuario)).thenReturn(usuarioRespuesta);
        // When
        UserRespuesta resultado = userService.findByEmailDTO("juan@dygames.cl");
        // Then
        assertNotNull(resultado);
        assertEquals("juan@dygames.cl", resultado.getEmail());
    }

    @Test
    void findDTOList_debeRetornarListaDeDTO() {
        // Given
        when(userRepository.findAll()).thenReturn(List.of(usuario));
        when(userMapper.toDTOList(List.of(usuario))).thenReturn(List.of(usuarioRespuesta));
        // When
        List<UserRespuesta> resultado = userService.findDTOList();
        // Then
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("gamer123", resultado.get(0).getUsername());
    }
}
