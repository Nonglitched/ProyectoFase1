use db_tierra_de_artesanos;
CREATE TABLE articulos (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    precio_venta DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL);
SELECT * FROM articulos;