package com.tierra.de.artesanos.almacen.model;

public class Articulo {
    private int codigo;
    private String descripcion;
    private double precioVenta;
    private int stock;

    public Articulo() {}

    public Articulo(int codigo, String descripcion, double precioVenta, int stock) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.stock = stock;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Articulo [codigo=" + codigo + ", descripcion=" + descripcion + ", precioVenta=" + precioVenta + ", stock=" + stock + "]";
    }
}
