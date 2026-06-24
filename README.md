Equipo de Desarrollo

| Isaac Rivas | Game Service y User Service — implementación y documentación Swagger |
| Igor Rojas | Category Service y Library Service — implementación y documentación Swagger — Infraestructura general |

---

Microservicios Implementados

| Servicio | Descripción | Documentado |
|---|---|---|
| eureka-server | Registro y descubrimiento de servicios | — |
| config-server | Configuración centralizada | — |
| api-gateway | Puerta de entrada única y enrutamiento | — |
| auth-service | Autenticación, registro y login | No |
| user-service | Gestión de perfiles de usuario | Sí — Isaac Rivas |
| game-service | Catálogo de videojuegos | Sí — Isaac Rivas |
| category-service | Categorías de videojuegos | Sí — Igor Rojas |
| library-service | Biblioteca personal de juegos del usuario | Sí — Igor Rojas |
| developer-service | Gestión de desarrolladoras | No |
| cart-service | Carrito de compras | No |
| order-service | Órdenes de compra | No |
| payment-service | Procesamiento de pagos | No |
| review-service | Reseñas y calificaciones | No |

---

Rutas del Gateway

Todas las peticiones entran por `http://localhost:8080`. A continuación las rutas disponibles por servicio.

Auth — /api/v1/auth
| Método | Ruta | Descripción |
|---|---|---|
| POST | /api/v1/auth/registrar | Registrar cuenta |
| POST | /api/v1/auth/login | Iniciar sesión |
| GET | /api/v1/auth | Listar cuentas |
| GET | /api/v1/auth/{id} | Buscar por ID |
| PUT | /api/v1/auth/{id} | Actualizar rol o estado |
| DELETE | /api/v1/auth/{id} | Eliminar cuenta |

 Usuarios — /api/v1/usuarios
| Método | Ruta | Descripción |
|---|---|---|
| GET | /api/v1/usuarios | Listar usuarios |
| GET | /api/v1/usuarios/{id} | Buscar por ID |
| POST | /api/v1/usuarios | Registrar usuario |
| PUT | /api/v1/usuarios/{id} | Actualizar perfil |
| DELETE | /api/v1/usuarios/{id} | Eliminar usuario |
| GET | /api/v1/usuarios/listado | Listar con detalle (DTO) |
| GET | /api/v1/usuarios/listado/{id} | Buscar con detalle por ID (DTO) |
| GET | /api/v1/usuarios/username/{username} | Buscar por username |
| GET | /api/v1/usuarios/email/{email} | Buscar por email |

 Juegos — /api/v1/juegos
| Método | Ruta | Descripción |
|---|---|---|
| GET | /api/v1/juegos | Listar juegos |
| GET | /api/v1/juegos/{id} | Buscar por ID |
| POST | /api/v1/juegos | Registrar juego |
| PUT | /api/v1/juegos/{id} | Actualizar juego |
| DELETE | /api/v1/juegos/{id} | Eliminar juego |
| GET | /api/v1/juegos/listado | Listar con detalle (DTO) |
| GET | /api/v1/juegos/listado/{id} | Buscar con detalle por ID (DTO) |
| GET | /api/v1/juegos/categoria/{categoriaId} | Filtrar por categoría |
| GET | /api/v1/juegos/activos | Listar juegos activos |
| GET | /api/v1/juegos/precio?min={min}&max={max} | Filtrar por rango de precio |
| GET | /api/v1/juegos/desarrollador/{desarrolladorId} | Filtrar por desarrollador |
| GET | /api/v1/juegos/buscar/{titulo} | Buscar por título |

Categorias — /api/v1/categorias
| Método | Ruta | Descripción |
|---|---|---|
| GET | /api/v1/categorias | Listar categorías |
| GET | /api/v1/categorias/{id} | Buscar por ID |
| POST | /api/v1/categorias | Registrar categoría |
| PUT | /api/v1/categorias/{id} | Actualizar categoría |
| DELETE | /api/v1/categorias/{id} | Eliminar categoría |
| GET | /api/v1/categorias/listado | Listar con detalle (DTO) |
| GET | /api/v1/categorias/listado/{id} | Buscar con detalle por ID (DTO) |
| GET | /api/v1/categorias/nombre/{nombre} | Buscar por nombre |

Biblioteca — /api/v1/biblioteca
| Método | Ruta | Descripción |
|---|---|---|
| GET | /api/v1/biblioteca | Listar biblioteca |
| GET | /api/v1/biblioteca/{id} | Buscar por ID |
| POST | /api/v1/biblioteca | Agregar juego a la biblioteca |
| DELETE | /api/v1/biblioteca/{id} | Eliminar registro |
| GET | /api/v1/biblioteca/listado | Listar con detalle (DTO) |
| GET | /api/v1/biblioteca/listado/{id} | Buscar con detalle por ID (DTO) |
| GET | /api/v1/biblioteca/usuario/{usuarioId} | Biblioteca de un usuario |
| GET | /api/v1/biblioteca/juego/{juegoId} | Usuarios que poseen un juego |
| GET | /api/v1/biblioteca/verificar?usuarioId={id}&juegoId={id} | Verificar si un usuario tiene un juego |

Desarrolladores — /api/v1/desarrolladores
| Método | Ruta | Descripción |
|---|---|---|
| GET | /api/v1/desarrolladores | Listar desarrolladoras |
| GET | /api/v1/desarrolladores/{id} | Buscar por ID |
| POST | /api/v1/desarrolladores | Registrar desarrolladora |
| PUT | /api/v1/desarrolladores/{id} | Actualizar desarrolladora |
| DELETE | /api/v1/desarrolladores/{id} | Eliminar desarrolladora |

 Carrito — /api/v1/carrito
| Método | Ruta | Descripción |
|---|---|---|
| GET | /api/v1/carrito | Listar items |
| GET | /api/v1/carrito/{id} | Buscar por ID |
| POST | /api/v1/carrito | Agregar item |
| DELETE | /api/v1/carrito/{id} | Eliminar item |
| DELETE | /api/v1/carrito/vaciar/{usuarioId} | Vaciar carrito de un usuario |
| GET | /api/v1/carrito/usuario/{usuarioId} | Ver carrito de un usuario |

 Ordenes — /api/v1/ordenes
| Método | Ruta | Descripción |
|---|---|---|
| GET | /api/v1/ordenes | Listar órdenes |
| GET | /api/v1/ordenes/{id} | Buscar por ID |
| POST | /api/v1/ordenes/crear/{usuarioId} | Crear orden desde el carrito |
| PUT | /api/v1/ordenes/{id} | Actualizar orden |
| DELETE | /api/v1/ordenes/{id} | Eliminar orden |
| GET | /api/v1/ordenes/usuario/{usuarioId} | Órdenes de un usuario |
| GET | /api/v1/ordenes/estado/{estado} | Filtrar por estado |

 Pagos — /api/v1/pagos
| Método | Ruta | Descripción |
|---|---|---|
| GET | /api/v1/pagos | Listar pagos |
| GET | /api/v1/pagos/{id} | Buscar por ID |
| POST | /api/v1/pagos | Registrar pago |
| PUT | /api/v1/pagos/{id} | Actualizar pago |
| DELETE | /api/v1/pagos/{id} | Eliminar pago |
| GET | /api/v1/pagos/usuario/{usuarioId} | Pagos de un usuario |
| GET | /api/v1/pagos/orden/{ordenId} | Pagos de una orden |
| GET | /api/v1/pagos/estado/{estado} | Filtrar por estado |
| GET | /api/v1/pagos/metodo/{metodo} | Filtrar por método de pago |

 Reseñas — /api/v1/resenas
| Método | Ruta | Descripción |
|---|---|---|
| GET | /api/v1/resenas | Listar reseñas |
| GET | /api/v1/resenas/{id} | Buscar por ID |
| POST | /api/v1/resenas | Registrar reseña |
| PUT | /api/v1/resenas/{id} | Actualizar reseña |
| DELETE | /api/v1/resenas/{id} | Eliminar reseña |
| GET | /api/v1/resenas/usuario/{usuarioId} | Reseñas de un usuario |
| GET | /api/v1/resenas/juego/{juegoId} | Reseñas de un juego |
| GET | /api/v1/resenas/calificacion/{calificacion} | Filtrar por calificación |

---

 Documentacion Swagger

El Swagger UI centralizado agrega la documentación de los cuatro servicios documentados en un solo lugar:

```
http://localhost:8080/swagger-ui.html
```

Desde el selector desplegable se puede cambiar entre user-service, game-service, category-service y library-service.

También se puede acceder directamente a cada servicio cuando se ejecuta en local:

| Servicio | URL |
|---|---|
| user-service | http://localhost:8082/swagger-ui.html |
| game-service | http://localhost:8083/swagger-ui.html |
| category-service | http://localhost:8084/swagger-ui.html |
| library-service | http://localhost:8086/swagger-ui.html |

---

 Ejecucion con Docker

Requisito: Docker Desktop instalado y en ejecución.

```bash
docker compose up --build
```

Esperar aproximadamente 60 segundos hasta que todos los contenedores estén listos. Se puede verificar el estado de los servicios registrados en Eureka:

```
http://localhost:8761
```

Para detener:

```bash
docker compose down

# Para detener y borrar los datos de la base de datos
docker compose down -v
---

## Ejecucion Local con XAMPP

Requisitos: Java 21, Maven 3.9+, XAMPP con MySQL activo en el puerto 3306.

### Paso 1 — Crear las bases de datos

Abrir phpMyAdmin en `http://localhost/phpmyadmin`, ir a la pestaña SQL y ejecutar:

```sql
CREATE DATABASE IF NOT EXISTS db_auth      CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_user      CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_game      CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_category  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_library   CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_developer CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_cart      CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_order     CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_payment   CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_review    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

 Paso 2 — Iniciar los servicios en orden

1. config-server
2. eureka-server
3. auth-service
4. user-service
5. game-service
6. category-service
7. library-service
8. developer-service
9. cart-service
10. order-service
11. payment-service
12. review-service
13. api-gateway

 Paso 3 — Verificar

```
http://localhost:8761                  
http://localhost:8080/swagger-ui.html  <- Swagger UI 
```
