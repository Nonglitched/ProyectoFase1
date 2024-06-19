package com.tierra.de.artesanos.almacen.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.tierra.de.artesanos.almacen.dao.ArticuloDAO;
import com.tierra.de.artesanos.almacen.model.Articulo;

public class App {
    private static ArticuloDAO articuloDAO = new ArticuloDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Ingresar nuevo producto");
            System.out.println("2. Agregar stock a producto existente");
            System.out.println("3. Actualizar precio de producto");
            System.out.println("4. Ver listado de artículos");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    ingresarNuevoProducto();
                    break;
                case 2:
                    agregarStockProducto();
                    break;
                case 3:
                    actualizarPrecioProducto();
                    break;
                case 4:
                    verListadoArticulos();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    private static void ingresarNuevoProducto() {
        System.out.print("Descripción: ");
        String descripcion = scanner.next();
        System.out.print("Precio de venta: ");
        double precioVenta = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();

        Articulo articulo = new Articulo(0, descripcion, precioVenta, stock);
        try {
            articuloDAO.insertArticulo(articulo);
            System.out.println("Producto ingresado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void agregarStockProducto() {
        System.out.print("Código del producto: ");
        int codigo = scanner.nextInt();
        System.out.print("Cantidad a agregar: ");
        int cantidad = scanner.nextInt();

        try {
            Articulo articulo = articuloDAO.selectArticulo(codigo);
            if (articulo != null) {
                articulo.setStock(articulo.getStock() + cantidad);
                articuloDAO.updateArticulo(articulo);
                System.out.println("Stock actualizado con éxito.");
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void actualizarPrecioProducto() {
        System.out.print("Código del producto: ");
        int codigo = scanner.nextInt();
        System.out.print("Nuevo precio: ");
        double nuevoPrecio = scanner.nextDouble();

        try {
            Articulo articulo = articuloDAO.selectArticulo(codigo);
            if (articulo != null) {
                articulo.setPrecioVenta(nuevoPrecio);
                articuloDAO.updateArticulo(articulo);
                System.out.println("Precio actualizado con éxito.");
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void verListadoArticulos() {
        List<Articulo> articulos = articuloDAO.selectAllArticulos();
		for (Articulo articulo : articulos) {
		    System.out.println(articulo);
		}
    }
}