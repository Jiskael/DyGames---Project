CREATE TABLE auth (
                      id        BIGINT AUTO_INCREMENT PRIMARY KEY,
                      email     VARCHAR(150) NOT NULL UNIQUE,
                      password  VARCHAR(255) NOT NULL,
                      rol       VARCHAR(20)  NOT NULL DEFAULT 'USER',
                      activo    BOOLEAN      NOT NULL DEFAULT TRUE,
                      creado_en DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- Datos de prueba (password de todos: 123456)
INSERT INTO auth (email, password, rol, activo) VALUES
('jiskael@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LpMSurdU3ia', 'USER',  true),
('carlos@gmail.com',  '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LpMSurdU3ia', 'USER',  true),
('admin@dygames.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LpMSurdU3ia', 'ADMIN', true);
