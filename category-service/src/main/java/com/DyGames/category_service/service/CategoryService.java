package com.DyGames.category_service.service;

import com.DyGames.category_service.dto.CategoryRespuesta;
import com.DyGames.category_service.mapper.CategoryMapper;
import com.DyGames.category_service.model.Category;
import com.DyGames.category_service.repository.CategoryRepository;
import com.DyGames.category_service.exception.NombreCategoriaExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    // Empezamos a hacer el CRUD y despues lo mismo con user-service

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category save(Category category) {
        if (categoryRepository.existsByNombre(category.getNombre())) {
            throw new NombreCategoriaExisteException("El nombre ya existe en el sistema");
        }
        return categoryRepository.save(category);
    }

    public Category update(Long id, Category category) {
        Category cat = categoryRepository.findById(id).orElse(null);
        if (cat == null) return null;
        cat.setNombre(category.getNombre());
        cat.setDescripcion(category.getDescripcion());
        cat.setIconUrl(category.getIconUrl());
        return categoryRepository.save(cat);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    // Metodos por DTO gracias al mapper
    public CategoryRespuesta findDTO(Long id) {
        return categoryMapper.toDTO(findById(id));
    }

    public List<CategoryRespuesta> findDTOList() {
        return categoryMapper.toDTOList(findAll());
    }

    public CategoryRespuesta findByNombreDTO(String nombre) {
        Category cat = categoryRepository.findByNombre(nombre).orElse(null);
        return categoryMapper.toDTO(cat);
    }
}
