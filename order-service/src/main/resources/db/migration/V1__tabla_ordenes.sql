CREATE TABLE ordenes (
                         id         BIGINT AUTO_INCREMENT PRIMARY KEY,
                         usuario_id BIGINT NOT NULL,
                         total      DOUBLE NOT NULL,
                         estado     VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
                         creado_en  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);