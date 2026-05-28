package com.DyGames.developer_service.repository;

import com.DyGames.developer_service.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Optional<Developer> findByNombreContainingIgnoreCase(String nombre);
}
