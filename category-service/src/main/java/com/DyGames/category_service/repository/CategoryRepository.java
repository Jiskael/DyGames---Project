package com.DyGames.category_service.repository;


import com.DyGames.category_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String nombre);
    boolean existsByName(String nombre);
}
