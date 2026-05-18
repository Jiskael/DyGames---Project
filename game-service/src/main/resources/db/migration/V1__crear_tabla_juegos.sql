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
-- Datos de prueba
INSERT INTO juegos (titulo, descripcion, desarrollador, precio, fecha_lanzamiento, categoria_id, activo) VALUES
('God of War',        'Aventura epica nordica',       'Santa Monica Studio', 59.99, '2018-04-20', 1, true),
('FIFA 24',           'Futbol de ultima generacion',  'EA Sports',           49.99, '2023-09-29', 3, true),
('Elden Ring',        'RPG de mundo abierto',         'FromSoftware',        59.99, '2022-02-25', 2, true),
('Resident Evil 4',   'Survival horror clasico',      'Capcom',              39.99, '2023-03-24', 1, true),
('Age of Empires IV', 'Estrategia en tiempo real',    'Relic Entertainment', 29.99, '2021-10-28', 4, true),
('The Witcher 3',     'RPG de fantasia medieval',     'CD Projekt Red',      19.99, '2015-05-19', 2, true),
('GTA V',             'Mundo abierto sandbox',        'Rockstar Games',      29.99, '2013-09-17', 1, true);
