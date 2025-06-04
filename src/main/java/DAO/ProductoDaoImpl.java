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
        String sql = "INSERT INTO producto (nombre, descripcion, precio_compra, precio_venta, cantidad, id_categoria, id_proveedor, fecha_ingreso, fecha_vencimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecioCompra());
            ps.setDouble(4, producto.getPrecioVenta());
            ps.setInt(5, producto.getCantidad());
            ps.setInt(6, producto.getIdCategoria());
            ps.setInt(7, producto.getIdProveedor());
            ps.setDate(8, Date.valueOf(producto.getFechaIngreso()));
            if (producto.getFechaVencimiento() != null) {
                ps.setDate(9, Date.valueOf(producto.getFechaVencimiento()));
            } else {
                ps.setNull(9, Types.DATE);
            }

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✔ Producto insertado correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar producto.");
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecioCompra(rs.getDouble("precio_compra"));
                p.setPrecioVenta(rs.getDouble("precio_venta"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setIdProveedor(rs.getInt("id_proveedor"));
                p.setFechaIngreso(rs.getDate("fecha_ingreso").toLocalDate());

                Date fechaVenc = rs.getDate("fecha_vencimiento");
                if (fechaVenc != null) {
                    p.setFechaVencimiento(fechaVenc.toLocalDate());
                } else {
                    p.setFechaVencimiento(null);
                }

                productos.add(p);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al obtener productos.");
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public Producto obtenerProductoPorId(int id) {
        Producto producto = null;
        String sql = "SELECT * FROM producto WHERE id_producto = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto();
                    producto.setIdProducto(rs.getInt("id_producto"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setPrecioCompra(rs.getDouble("precio_compra"));
                    producto.setPrecioVenta(rs.getDouble("precio_venta"));
                    producto.setCantidad(rs.getInt("cantidad"));
                    producto.setIdCategoria(rs.getInt("id_categoria"));
                    producto.setIdProveedor(rs.getInt("id_proveedor"));
                    producto.setFechaIngreso(rs.getDate("fecha_ingreso").toLocalDate());

                    Date fechaVenc = rs.getDate("fecha_vencimiento");
                    if (fechaVenc != null) {
                        producto.setFechaVencimiento(fechaVenc.toLocalDate());
                    } else {
                        producto.setFechaVencimiento(null);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener producto por ID.");
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public void actualizarProducto(Producto producto) {
        String sql = "UPDATE producto SET nombre = ?, descripcion = ?, precio_compra = ?, precio_venta = ?, cantidad = ?, id_categoria = ?, id_proveedor = ?, fecha_ingreso = ?, fecha_vencimiento = ? WHERE id_producto = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecioCompra());
            ps.setDouble(4, producto.getPrecioVenta());
            ps.setInt(5, producto.getCantidad());
            ps.setInt(6, producto.getIdCategoria());
            ps.setInt(7, producto.getIdProveedor());
            ps.setDate(8, Date.valueOf(producto.getFechaIngreso()));
            if (producto.getFechaVencimiento() != null) {
                ps.setDate(9, Date.valueOf(producto.getFechaVencimiento()));
            } else {
                ps.setNull(9, Types.DATE);
            }
            ps.setInt(10, producto.getIdProducto());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✔ Producto actualizado correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar producto.");
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarProducto(int id) {
        String sql = "DELETE FROM producto WHERE id_producto = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✔ Producto eliminado correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar producto.");
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Producto> obtenerProductosBajoStock() {   // sin parámetro → límite fijo = 10
    List<Producto> lista = new ArrayList<>();
    String sql = "SELECT * FROM producto WHERE cantidad <= 10";

    try (Connection conn = Conexiondb.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Producto p = new Producto();
            p.setIdProducto(rs.getInt("id_producto"));
            p.setNombre(rs.getString("nombre"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setCantidad(rs.getInt("cantidad"));
            p.setPrecioCompra(rs.getDouble("precio_compra"));
            p.setPrecioVenta(rs.getDouble("precio_venta"));
            p.setIdCategoria(rs.getInt("id_categoria"));
            p.setIdProveedor(rs.getInt("id_proveedor"));
            p.setFechaIngreso(rs.getDate("fecha_ingreso").toLocalDate());
            Date fv = rs.getDate("fecha_vencimiento");
            p.setFechaVencimiento(fv != null ? fv.toLocalDate() : null);

            lista.add(p);
        }

    } catch (SQLException e) {
        System.err.println("❌ Error al obtener productos con bajo stock.");
        e.printStackTrace();
    }
    return lista;
}
}
