CREATE TABLE ordenes (
                         id         BIGINT AUTO_INCREMENT PRIMARY KEY,
                         usuario_id BIGINT NOT NULL,
                         total      DOUBLE NOT NULL,
                         estado     VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
                         creado_en  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- Datos de prueba
INSERT INTO ordenes (usuario_id, total, estado) VALUES
(1, 119.98, 'PAGADO'),
(2,  49.99, 'PAGADO'),
(1,  59.99, 'PENDIENTE');
