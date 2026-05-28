package com.DyGames.developer_service.mapper;

import com.DyGames.developer_service.dto.DeveloperRespuesta;
import com.DyGames.developer_service.model.Developer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeveloperMapper {

    public DeveloperRespuesta toDTO(Developer developer) {
        if (developer == null) return null;
        DeveloperRespuesta dr = new DeveloperRespuesta();
        dr.setId(developer.getId());
        dr.setNombre(developer.getNombre());
        dr.setSitioWeb(developer.getSitioWeb());
        return dr;
    }

    public List<DeveloperRespuesta> toDTOList(List<Developer> lista) {
        return lista.stream().map(this::toDTO).toList();
    }
}
