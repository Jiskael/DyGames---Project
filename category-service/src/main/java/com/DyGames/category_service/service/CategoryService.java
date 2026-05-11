package com.DyGames.category_service.service;

import com.DyGames.category_service.dto.CategoryRequest;
import com.DyGames.category_service.dto.CategoryRespuesta;
import com.DyGames.category_service.model.Category;
import com.DyGames.category_service.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    //Primer metodo, crear categoria y validamos si existe una con el nombre similar o nop.
    public CategoryRespuesta crearCategory(CategoryRequest r){
        if (categoryRepository.existsByName(r.getNombre())) {
            throw new RuntimeException("El nombre existe en el sistema");
        }
        //Aqui pues similar al hacer el dto, creamos una nueva categoria y seteamos el nombre y los datos necesarios.
        Category category = new Category();
        category.setNombre(r.getNombre());
        category.setDescripcion(r.getDescripcion());
        category.setIconUrl(r.getIconUrl());

        //Y ahora lo guardamos y lo covertimos con el metodo DTO
        return DTO(categoryRepository.save(category));

    }

    //Y aqui convertimos categoria a categoria DTO (osea CategoryRespuesta xd)
    private CategoryRespuesta DTO(Category category) {
        CategoryRespuesta cr = new CategoryRespuesta();
        cr.setId(category.getId());
        cr.setNombre(category.getNombre());
        cr.setDescripcion(category.getDescripcion());
        cr.setIconUrl(category.getIconUrl());
        return cr;
    }

    //Ahora el metodo para obtener las categorias
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

}
