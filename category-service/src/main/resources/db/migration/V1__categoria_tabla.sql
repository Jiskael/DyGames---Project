CREATE TABLE categorias (
                            id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                            nombre        VARCHAR(100) NOT NULL UNIQUE,
                            descripcion VARCHAR(255),
                            icon_url    VARCHAR(255)
);

INSERT INTO categorias (nombre, descripcion, icon_url) VALUES
                                                         ('Accion',      'Juegos de accion y combate',     null),
                                                         ('RPG',         'Juegos de rol',                  null),
                                                         ('Deportes',    'Juegos deportivos',               null),
                                                         ('Estrategia',  'Juegos de estrategia',            null),
                                                         ('Aventura',    'Juegos de aventura y exploracion',null);