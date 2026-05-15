package com.DyGames.library_service.mapper;

import com.DyGames.library_service.dto.LibraryRespuesta;
import com.DyGames.library_service.model.Library;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class LibraryMapper {

    public LibraryRespuesta toDTO(Library library) {
        if (library == null) return null;
        LibraryRespuesta lr = new LibraryRespuesta();
        lr.setId(library.getId());
        lr.setUsuarioId(library.getUsuarioId());
        lr.setJuegoId(library.getJuegoId());
        lr.setOrdenId(library.getOrdenId());
        lr.setAdquiridoEn(library.getAdquiridoEn());
        return lr;
    }

    public List<LibraryRespuesta> toDTOList(List<Library> lista) {
        return lista.stream().map(this::toDTO).toList();
    }
}