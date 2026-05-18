CREATE TABLE resenas (
                         id         BIGINT AUTO_INCREMENT PRIMARY KEY,
                         usuario_id BIGINT NOT NULL,
                         juego_id   BIGINT NOT NULL,
                         calificacion INT NOT NULL,
                         comentario TEXT,
                         creado_en  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- Datos de prueba
INSERT INTO resenas (usuario_id, juego_id, calificacion, comentario, creado_en) VALUES
(1, 1, 5, 'Excelente juego, historia increible',     NOW()),
(1, 3, 4, 'Muy buen RPG, un poco dificil al inicio', NOW()),
(2, 2, 3, 'Entretenido pero repetitivo',             NOW());
