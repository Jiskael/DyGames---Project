CREATE TABLE resenas (
                         id         BIGINT AUTO_INCREMENT PRIMARY KEY,
                         usuario_id BIGINT NOT NULL,
                         juego_id   BIGINT NOT NULL,
                         calificacion INT NOT NULL,
                         comentario TEXT,
                         creado_en  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);