CREATE TABLE pagos (
                       id         BIGINT AUTO_INCREMENT PRIMARY KEY,
                       orden_id   BIGINT NOT NULL,
                       usuario_id BIGINT NOT NULL,
                       monto      DOUBLE NOT NULL,
                       metodo     VARCHAR(50) NOT NULL,
                       estado     VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
                       creado_en  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- Datos de prueba
INSERT INTO pagos (orden_id, usuario_id, monto, metodo, estado) VALUES
(1, 1, 59.99, 'TARJETA',  'PAGADO'),
(2, 2, 49.99, 'PAYPAL',   'PAGADO'),
(3, 1, 59.99, 'TARJETA',  'PENDIENTE');
