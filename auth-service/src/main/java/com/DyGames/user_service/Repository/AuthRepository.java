package com.DyGames.user_service.Repository;

import com.DyGames.user_service.Model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth,Long> {
    Optional<Auth> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Auth> findByRole(String role);

    List<Auth> findByActive(boolean active);
}
