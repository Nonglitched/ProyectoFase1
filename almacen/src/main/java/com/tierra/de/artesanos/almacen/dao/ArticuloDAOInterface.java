package com.tierra.de.artesanos.almacen.dao;

import java.sql.SQLException;
import java.util.List;

import com.tierra.de.artesanos.almacen.model.Articulo;

public interface ArticuloDAOInterface {
    void insertArticulo(Articulo articulo) throws SQLException;
    Articulo selectArticulo(int id);
    List<Articulo> selectAllArticulos();
    boolean deleteArticulo(int id) throws SQLException;
    boolean updateArticulo(Articulo articulo) throws SQLException;
}
