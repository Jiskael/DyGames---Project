package com.DyGames.auth_service.repository;

import com.DyGames.auth_service.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {

    //Pregunta 1:

    Optional<Auth> findByEmail(String email);
    boolean existsByEmail(String email);
}