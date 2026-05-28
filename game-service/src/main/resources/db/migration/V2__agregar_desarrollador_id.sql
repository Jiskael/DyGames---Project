-- Paso 1: agregar desarrollador_id si no existe
ALTER TABLE juegos ADD COLUMN IF NOT EXISTS desarrollador_id BIGINT NOT NULL DEFAULT 1;

-- Paso 2: asignar IDs segun nombre del desarrollador
UPDATE juegos SET desarrollador_id = 1 WHERE desarrollador = 'Santa Monica Studio';
UPDATE juegos SET desarrollador_id = 2 WHERE desarrollador = 'EA Sports';
UPDATE juegos SET desarrollador_id = 3 WHERE desarrollador = 'FromSoftware';
UPDATE juegos SET desarrollador_id = 4 WHERE desarrollador = 'Capcom';
UPDATE juegos SET desarrollador_id = 5 WHERE desarrollador = 'Relic Entertainment';
UPDATE juegos SET desarrollador_id = 6 WHERE desarrollador = 'CD Projekt Red';
UPDATE juegos SET desarrollador_id = 7 WHERE desarrollador = 'Rockstar Games';

-- Paso 3: eliminar columna antigua si todavia existe
ALTER TABLE juegos DROP COLUMN IF EXISTS desarrollador;
