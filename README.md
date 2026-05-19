# DyGames - Proyecto Semestral

## Orden de arranque
1. config-server    → puerto 8888
2. eureka-server    → puerto 8761
3. api-gateway      → puerto 8080
4. category-service → puerto 8084
5. auth-service     → puerto 8085
6. user-service     → puerto 8082
7. game-service     → puerto 8083
8. library-service  → puerto 8086
9. payment-service  → puerto 8087
10. review-service  → puerto 8088
11. cart-service    → puerto 8089
12. order-service   → puerto 8090

## Antes de arrancar
1. Iniciar XAMPP (MySQL)
2. Ejecutar CREAR_BASES_DE_DATOS.sql en phpMyAdmin
3. Arrancar en el orden indicado

## Endpoints principales
- Categorias:  GET http://localhost:8084/api/v1/categorias
- Juegos:      GET http://localhost:8083/api/v1/juegos
- Usuarios:    GET http://localhost:8082/api/v1/usuarios
- Auth:        POST http://localhost:8085/api/v1/auth/login
- Carrito:     GET http://localhost:8089/api/v1/carrito
- Ordenes:     POST http://localhost:8090/api/v1/ordenes/crear/{usuarioId}
- Pagos:       GET http://localhost:8087/api/v1/pagos
- Biblioteca:  GET http://localhost:8086/api/v1/biblioteca
- Resenas:     GET http://localhost:8088/api/v1/resenas
