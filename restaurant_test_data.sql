-- Script SQL para insertar restaurantes de prueba
-- Ejecutar este script en la base de datos PostgreSQL

-- Conectar a la base de datos
-- psql -h localhost -U postgres -d comandapro

-- Insertar restaurante principal de prueba
INSERT INTO restaurants (name, email, phone, address, active, created_at) 
VALUES (
    'Restaurante El Buen Sabor',
    'contacto@elbuensabor.com',
    1234567890,
    'Calle Principal 123, Ciudad Central',
    true,
    NOW()
);

-- Insertar segundo restaurante de prueba
INSERT INTO restaurants (name, email, phone, address, active, created_at) 
VALUES (
    'Pizzería Italiana',
    'info@pizzeriaitaliana.com',
    0987654321,
    'Avenida Italia 456, Zona Norte',
    true,
    NOW()
);

-- Insertar restaurante inactivo para pruebas
INSERT INTO restaurants (name, email, phone, address, active, created_at) 
VALUES (
    'Café Cerrado',
    'cerrado@cafe.com',
    5555555555,
    'Calle Secundaria 789, Zona Sur',
    false,
    NOW()
);

-- Verificar que se insertaron correctamente
SELECT * FROM restaurants;

-- Mostrar solo los restaurantes activos
SELECT id, name, email, address, active FROM restaurants WHERE active = true;
