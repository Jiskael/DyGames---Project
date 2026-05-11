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

    // Metodo para obtener categorias
    public List<CategoryRespuesta> ObtenerTodasLasCategorias() {
        List<CategoryRespuesta> cr = new ArrayList<>();
        for (Category c : categoryRepository.findAll()) {
            cr.add(DTO(c));
        }
        return cr;
    }

    //Metodo para buscar por id
    public CategoryRespuesta BuscarPorId(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("El id de la categoria no existe"));
        return DTO(category);
    }

    //Metodo para actualizar
    public CategoryRespuesta Actualizar(Long id, CategoryRequest r) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe el id de la categoria"));

        category.setDescripcion(r.getDescripcion());
        category.setIconUrl(r.getIconUrl());
        category.setNombre(r.getNombre());
        return DTO(categoryRepository.save(category));

    }

    //Metodo para eliminar
    public void Eliminar(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Categoria no encontrada");
        }
        categoryRepository.deleteById(id);
    }

    //Metodo para buscar categoria por nombre
    public CategoryRespuesta BuscarPorNombre(String nombre) {
        Category category = categoryRepository.findByName(nombre).orElseThrow(() -> new RuntimeException("El nombre no existe"));
        return DTO(category);
    }
}
