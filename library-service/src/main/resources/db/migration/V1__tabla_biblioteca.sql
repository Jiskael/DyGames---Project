CREATE TABLE biblioteca (
                            id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                            usuario_id   BIGINT NOT NULL,
                            juego_id     BIGINT NOT NULL,
                            orden_id     BIGINT,
                            adquirido_en DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);