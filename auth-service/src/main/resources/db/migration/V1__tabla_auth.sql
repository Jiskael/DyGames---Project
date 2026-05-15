CREATE TABLE auth (
                      id        BIGINT AUTO_INCREMENT PRIMARY KEY,
                      email     VARCHAR(150) NOT NULL UNIQUE,
                      password  VARCHAR(255) NOT NULL,
                      rol       VARCHAR(20)  NOT NULL DEFAULT 'USER',
                      activo    BOOLEAN      NOT NULL DEFAULT TRUE,
                      creado_en DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
);