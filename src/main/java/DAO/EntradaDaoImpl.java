package DAO;

import Conexion.Conexiondb;
import Modelo.Entrada;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntradaDaoImpl implements EntradaDAO {

    @Override
    public boolean registrarEntrada(Entrada entrada) {
        String sql = "INSERT INTO entradas (id_producto, id_proveedor, cantidad, precio_compra, precio_venta, margen, fecha_ingreso, fecha_vencimiento, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlActualizarStock = "UPDATE productos SET stock = stock + ?, precio_venta = ? WHERE id_producto = ?";

        try (Connection conn = Conexiondb.getConexion()) {
            conn.setAutoCommit(false); // iniciar transacción

            try (PreparedStatement stmt = conn.prepareStatement(sql); PreparedStatement stmtStock = conn.prepareStatement(sqlActualizarStock)) {

                // Insertar la entrada
                stmt.setInt(1, entrada.getIdProducto());
                stmt.setInt(2, entrada.getIdProveedor());
                stmt.setInt(3, entrada.getCantidad());
                stmt.setDouble(4, entrada.getPrecioCompra());
                stmt.setDouble(5, entrada.getPrecioVenta());
                stmt.setDouble(6, entrada.getMargen());
                stmt.setTimestamp(7, new Timestamp(entrada.getFechaIngreso().getTime()));

                if (entrada.getFechaVencimiento() != null) {
                    stmt.setDate(8, new java.sql.Date(entrada.getFechaVencimiento().getTime()));
                } else {
                    stmt.setNull(8, Types.DATE);
                }

                stmt.setInt(9, entrada.getIdUsuario());

                stmt.executeUpdate();

                stmtStock.setInt(1, entrada.getCantidad());
                stmtStock.setDouble(2, entrada.getPrecioVenta());
                stmtStock.setInt(3, entrada.getIdProducto());

                stmtStock.executeUpdate();

                conn.commit();
                return true;

            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarEntrada(Entrada entrada) {
        String sql = "UPDATE entradas SET id_producto=?, id_proveedor=?, cantidad=?, precio_compra=?, precio_venta=?, margen=?, fecha_ingreso=?, fecha_vencimiento=?, id_usuario=? WHERE id_entrada=?";

        try (Connection conn = Conexiondb.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, entrada.getIdProducto());
            stmt.setInt(2, entrada.getIdProveedor());
            stmt.setInt(3, entrada.getCantidad());
            stmt.setDouble(4, entrada.getPrecioCompra());
            stmt.setDouble(5, entrada.getPrecioVenta());
            stmt.setDouble(6, entrada.getMargen()); // ← Agregado margen

            stmt.setTimestamp(7, new Timestamp(entrada.getFechaIngreso().getTime()));

            if (entrada.getFechaVencimiento() != null) {
                stmt.setDate(8, new java.sql.Date(entrada.getFechaVencimiento().getTime()));
            } else {
                stmt.setNull(8, Types.DATE);
            }

            stmt.setInt(9, entrada.getIdUsuario());
            stmt.setInt(10, entrada.getIdEntrada());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarEntrada(int idEntrada) {
        String sql = "DELETE FROM entradas WHERE id_entrada = ?";
        try (Connection conn = Conexiondb.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEntrada);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Entrada> listarEntradas() {
        List<Entrada> lista = new ArrayList<>();
        String sql = "SELECT * FROM entradas";

        try (Connection conn = Conexiondb.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Entrada entrada = new Entrada();
                entrada.setIdEntrada(rs.getInt("id_entrada"));
                entrada.setIdProducto(rs.getInt("id_producto"));
                entrada.setIdProveedor(rs.getInt("id_proveedor"));
                entrada.setCantidad(rs.getInt("cantidad"));
                entrada.setPrecioCompra(rs.getDouble("precio_compra"));
                entrada.setPrecioVenta(rs.getDouble("precio_venta"));
                entrada.setMargen(rs.getDouble("margen")); // ← Agregado margen
                entrada.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));
                entrada.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                entrada.setIdUsuario(rs.getInt("id_usuario"));
                lista.add(entrada);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public Entrada obtenerEntradaPorId(int idEntrada) {
        String sql = "SELECT * FROM entradas WHERE id_entrada = ?";
        try (Connection conn = Conexiondb.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEntrada);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Entrada entrada = new Entrada();
                    entrada.setIdEntrada(rs.getInt("id_entrada"));
                    entrada.setIdProducto(rs.getInt("id_producto"));
                    entrada.setIdProveedor(rs.getInt("id_proveedor"));
                    entrada.setCantidad(rs.getInt("cantidad"));
                    entrada.setPrecioCompra(rs.getDouble("precio_compra"));
                    entrada.setPrecioVenta(rs.getDouble("precio_venta"));
                    entrada.setMargen(rs.getDouble("margen"));  // <-- Agregado margen
                    entrada.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));
                    entrada.setFechaVencimiento(rs.getDate("fecha_vencimiento"));
                    entrada.setIdUsuario(rs.getInt("id_usuario"));
                    return entrada;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
