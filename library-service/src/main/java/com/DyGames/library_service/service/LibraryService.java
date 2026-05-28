package com.DyGames.library_service.service;

import com.DyGames.library_service.dto.LibraryRespuesta;
import com.DyGames.library_service.mapper.LibraryMapper;
import com.DyGames.library_service.model.Library;
import com.DyGames.library_service.repository.LibraryRepository;
import com.DyGames.library_service.exception.JuegoYaEnBibliotecaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private LibraryMapper libraryMapper;

    public List<Library> findAll() {
        return libraryRepository.findAll();
    }

    public Library findById(Long id) {
        return libraryRepository.findById(id).orElse(null);
    }

    public Library save(Library library) {
        if (libraryRepository.existsByUsuarioIdAndJuegoId(
                library.getUsuarioId(), library.getJuegoId())) {
            throw new JuegoYaEnBibliotecaException("El usuario ya tiene este juego en su biblioteca");
        }
        return libraryRepository.save(library);
    }

    public void delete(Long id) {
        libraryRepository.deleteById(id);
    }

    // Metodos DTO
    public LibraryRespuesta findDTO(Long id) {
        return libraryMapper.toDTO(findById(id));
    }

    public List<LibraryRespuesta> findDTOList() {
        return libraryMapper.toDTOList(findAll());
    }

    // Reportes
    public List<LibraryRespuesta> findByUsuario(Long usuarioId) {
        return libraryMapper.toDTOList(libraryRepository.findByUsuarioId(usuarioId));
    }

    public List<LibraryRespuesta> findByJuego(Long juegoId) {
        return libraryMapper.toDTOList(libraryRepository.findByJuegoId(juegoId));
    }

    public boolean tieneJuego(Long usuarioId, Long juegoId) {
        return libraryRepository.existsByUsuarioIdAndJuegoId(usuarioId, juegoId);
    }
}