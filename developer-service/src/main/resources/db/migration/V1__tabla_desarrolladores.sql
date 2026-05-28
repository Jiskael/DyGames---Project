CREATE TABLE desarrolladores (
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(150) NOT NULL UNIQUE,
    sitio_web VARCHAR(255)
);

-- Datos de prueba (empresas reales referenciadas en game-service)
INSERT INTO desarrolladores (nombre, sitio_web) VALUES
('Santa Monica Studio', 'https://sms.playstation.com'),
('EA Sports',           'https://www.ea.com/ea-sports'),
('FromSoftware',        'https://www.fromsoftware.jp'),
('Capcom',              'https://www.capcom.com'),
('Relic Entertainment', 'https://www.relic.com'),
('CD Projekt Red',      'https://www.cdprojektred.com'),
('Rockstar Games',      'https://www.rockstargames.com');
