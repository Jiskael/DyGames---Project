CREATE TABLE pagos (
                       id         BIGINT AUTO_INCREMENT PRIMARY KEY,
                       orden_id   BIGINT NOT NULL,
                       usuario_id BIGINT NOT NULL,
                       monto      DOUBLE NOT NULL,
                       metodo     VARCHAR(50) NOT NULL,
                       estado     VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
                       creado_en  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);