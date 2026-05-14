CREATE TABLE juegos (
                        id            BIGINT AUTO_INCREMENT PRIMARY KEY,
                        titulo        VARCHAR(150) NOT NULL,
                        descripcion   TEXT,
                        desarrollador VARCHAR(100) NOT NULL,
                        precio        DOUBLE NOT NULL,
                        fecha_lanzamiento DATE,
                        cover_url     VARCHAR(255),
                        categoria_id  BIGINT NOT NULL,
                        activo        BOOLEAN NOT NULL DEFAULT TRUE
);