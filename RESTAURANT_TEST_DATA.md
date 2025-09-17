# Datos de Prueba - Restaurantes

Este documento explica cómo crear y usar restaurantes de prueba para las pruebas de la API de usuarios.

## 🏪 Restaurantes de Prueba Creados

### 1. **Restaurante El Buen Sabor**
- **ID**: 1 (se asigna automáticamente)
- **Nombre**: Restaurante El Buen Sabor
- **Email**: contacto@elbuensabor.com
- **Teléfono**: 1234567890
- **Dirección**: Calle Principal 123, Ciudad Central
- **Estado**: Activo

### 2. **Pizzería Italiana**
- **ID**: 2 (se asigna automáticamente)
- **Nombre**: Pizzería Italiana
- **Email**: info@pizzeriaitaliana.com
- **Teléfono**: 987654321
- **Dirección**: Avenida Italia 456, Zona Norte
- **Estado**: Activo

### 3. **Café Cerrado**
- **ID**: 3 (se asigna automáticamente)
- **Nombre**: Café Cerrado
- **Email**: cerrado@cafe.com
- **Teléfono**: 5555555555
- **Dirección**: Calle Secundaria 789, Zona Sur
- **Estado**: Inactivo

## 🚀 Métodos para Crear los Datos

### Método 1: Automático (Recomendado)
Los restaurantes se crean automáticamente al iniciar la aplicación gracias al archivo `data.sql`.

### Método 2: Endpoint REST
```bash
POST http://localhost:8080/restaurants/create-test-data
```

### Método 3: Script SQL Manual
```bash
# Conectar a PostgreSQL
psql -h localhost -U postgres -d comandapro

# Ejecutar el script
\i restaurant_test_data.sql
```

## 📝 Ejemplos de Uso con Usuarios

### Crear un usuario en el Restaurante El Buen Sabor (ID: 1)
```json
POST http://localhost:8080/users
Content-Type: application/json

{
  "restaurantId": 1,
  "fullName": "Juan Pérez García",
  "email": "juan.perez@elbuensabor.com",
  "password": "mesero123",
  "role": "WAITER",
  "active": true
}
```

### Crear un administrador en Pizzería Italiana (ID: 2)
```json
POST http://localhost:8080/users
Content-Type: application/json

{
  "restaurantId": 2,
  "fullName": "María González López",
  "email": "maria.gonzalez@pizzeriaitaliana.com",
  "password": "admin456",
  "role": "ADMIN",
  "active": true
}
```

### Obtener usuarios del Restaurante El Buen Sabor
```bash
GET http://localhost:8080/users/restaurant/1
```

### Obtener usuarios activos del Restaurante El Buen Sabor
```bash
GET http://localhost:8080/users/restaurant/1/active
```

## 🔍 Verificar Datos

### Ver todos los restaurantes
```bash
GET http://localhost:8080/restaurants
```

### Ver restaurantes activos
```bash
GET http://localhost:8080/restaurants/active
```

### Ver restaurante específico
```bash
GET http://localhost:8080/restaurants/1
```

## ⚠️ Notas Importantes

1. **IDs de Restaurantes**: Los IDs se asignan automáticamente por la base de datos
2. **Emails Únicos**: Cada restaurante tiene un email único
3. **Estado Activo**: Solo los restaurantes activos deberían usarse para crear usuarios
4. **Datos de Prueba**: Estos son datos de prueba, no usar en producción

## 🗑️ Limpiar Datos de Prueba

Si necesitas limpiar los datos de prueba:

```sql
-- CUIDADO: Esto eliminará TODOS los datos
DELETE FROM users;
DELETE FROM restaurants;
```

O reinicia la base de datos completamente.
