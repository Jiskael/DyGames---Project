package com.DyGames.category_service.mapper;


import com.DyGames.category_service.dto.CategoryRespuesta;
import com.DyGames.category_service.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {
    public CategoryRespuesta toDTO(Category category) {
        if (category == null) return null;
        CategoryRespuesta cr = new CategoryRespuesta();
        cr.setId(category.getId());
        cr.setNombre(category.getNombre());
        cr.setDescripcion(category.getDescripcion());
        cr.setIconUrl(category.getIconUrl());
        return cr;
    }

    public List<CategoryRespuesta> toDTOList(List<Category> lista) {
        return lista.stream().map(this::toDTO).toList();
    }
}
