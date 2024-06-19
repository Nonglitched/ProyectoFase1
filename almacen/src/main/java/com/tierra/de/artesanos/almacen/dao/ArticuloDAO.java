package com.tierra.de.artesanos.almacen.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tierra.de.artesanos.almacen.model.Articulo;

public class ArticuloDAO implements ArticuloDAOInterface {
    private String jdbcURL = "jdbc:mysql://localhost:3306/db_tierra_de_artesanos";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_ARTICULO_SQL = "INSERT INTO articulos (descripcion, precio_venta, stock) VALUES (?, ?, ?);";
    private static final String SELECT_ARTICULO_BY_ID = "SELECT codigo, descripcion, precio_venta, stock FROM articulos WHERE codigo = ?;";
    private static final String SELECT_ALL_ARTICULOS = "SELECT * FROM articulos;";
    private static final String DELETE_ARTICULO_SQL = "DELETE FROM articulos WHERE codigo = ?;";
    private static final String UPDATE_ARTICULO_SQL = "UPDATE articulos SET descripcion = ?, precio_venta= ?, stock = ? WHERE codigo = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertArticulo(Articulo articulo) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARTICULO_SQL)) {
            preparedStatement.setString(1, articulo.getDescripcion());
            preparedStatement.setDouble(2, articulo.getPrecioVenta());
            preparedStatement.setInt(3, articulo.getStock());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Articulo selectArticulo(int id) {
        Articulo articulo = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARTICULO_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descripcion = rs.getString("descripcion");
                double precioVenta = rs.getDouble("precio_venta");
                int stock = rs.getInt("stock");
                articulo = new Articulo(id, descripcion, precioVenta, stock);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return articulo;
    }

    @Override
    public List<Articulo> selectAllArticulos() {
        List<Articulo> articulos = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ARTICULOS);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String descripcion = rs.getString("descripcion");
                double precioVenta = rs.getDouble("precio_venta");
                int stock = rs.getInt("stock");
                articulos.add(new Articulo(codigo, descripcion, precioVenta, stock));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return articulos;
    }

    @Override
    public boolean deleteArticulo(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ARTICULO_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateArticulo(Articulo articulo) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ARTICULO_SQL);) {
            statement.setString(1, articulo.getDescripcion());
            statement.setDouble(2, articulo.getPrecioVenta());
            statement.setInt(3, articulo.getStock());
            statement.setInt(4, articulo.getCodigo());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}