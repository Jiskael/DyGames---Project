CREATE TABLE usuarios (
                          id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                          auth_id      BIGINT NOT NULL,
                          username     VARCHAR(100) NOT NULL UNIQUE,
                          nombre       VARCHAR(150),
                          pfp_url   VARCHAR(255),
                          email         VARCHAR(100),
                          creado_en    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
-- Datos de prueba
INSERT INTO usuarios (auth_id, username, nombre, pfp_url, email, creado_en) VALUES
(1, 'jiskael', 'Jiskael Garcia', null, 'jiskael@gmail.com', NOW()),
(2, 'carlos',  'Carlos Lopez',   null, 'carlos@gmail.com',  NOW()),
(3, 'admin',   'Administrador',  null, 'admin@dygames.com', NOW());
