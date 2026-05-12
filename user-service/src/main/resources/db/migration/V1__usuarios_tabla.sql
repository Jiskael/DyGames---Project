CREATE TABLE usuarios (
                          id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                          auth_id      BIGINT NOT NULL,
                          username     VARCHAR(100) NOT NULL UNIQUE,
                          nombre       VARCHAR(150),
                          pfp_url   VARCHAR(255),
                          email         VARCHAR(100),
                          creado_en    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);