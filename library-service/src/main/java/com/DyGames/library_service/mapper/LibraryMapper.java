package com.DyGames.library_service.mapper;

import com.DyGames.library_service.client.GameClient;
import com.DyGames.library_service.client.OrderClient;
import com.DyGames.library_service.client.UserClient;
import com.DyGames.library_service.dto.LibraryRespuesta;
import com.DyGames.library_service.model.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryMapper {

    @Autowired
    private UserClient userClient;

    @Autowired
    private GameClient gameClient;

    @Autowired
    private OrderClient orderClient;

    public LibraryRespuesta toDTO(Library library) {
        if (library == null) return null;
        LibraryRespuesta lr = new LibraryRespuesta();
        lr.setId(library.getId());
        lr.setUsuario(userClient.buscarUsuarioPorId(library.getUsuarioId()));
        lr.setJuego(gameClient.buscarJuegoPorId(library.getJuegoId()));
        if (library.getOrdenId() != null) {
            lr.setOrden(orderClient.buscarOrdenPorId(library.getOrdenId()));
        }
        lr.setAdquiridoEn(library.getAdquiridoEn());
        return lr;
    }

    public List<LibraryRespuesta> toDTOList(List<Library> lista) {
        return lista.stream().map(this::toDTO).toList();
    }
}
