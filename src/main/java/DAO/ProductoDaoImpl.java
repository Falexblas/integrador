package DAO;

import Conexion.Conexiondb;
import Modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDaoImpl implements ProductoDAO {

    private Connection conexion;

    public ProductoDaoImpl() {
        conexion = Conexiondb.getConexion();
    }

    @Override
    public void insertarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre,id_categoria) VALUES (?, ?)";

        try (Connection conn = Conexiondb.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());

            ps.setInt(2, producto.getIdCategoria());

            ps.executeUpdate();
            System.out.println("✔ Producto insertado.");

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar producto.");
            e.printStackTrace();
        }

    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT * FROM productos";

        try (Connection conn = Conexiondb.getConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setStock(rs.getInt("stock"));
                p.setPrecioVenta(rs.getDouble("precio_venta")); // <- ✅ este campo nuevo

                productos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    @Override
    public Producto obtenerProductoPorId(int id) {
        Producto producto = null;
        String sql = "SELECT * FROM productos WHERE id_producto = ?";
        try (Connection conn = Conexiondb.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                producto = new Producto();
                producto.setIdProducto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setIdCategoria(rs.getInt("id_categoria"));
                // completar con otros campos si tienes
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, id_categoria = ? WHERE id_producto = ?";
        try (Connection conn = Conexiondb.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getIdCategoria());
            stmt.setInt(3, producto.getIdProducto());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar producto.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarProducto(int idProducto) {
        String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (Connection conn = Conexiondb.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProducto);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar producto.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarStock(int idProducto, int diferencia) {
        String sql = "UPDATE productos SET stock = stock + ? WHERE id_producto = ?";
        try (Connection conn = Conexiondb.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, diferencia);
            ps.setInt(2, idProducto);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
