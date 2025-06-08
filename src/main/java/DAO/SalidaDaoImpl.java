package DAO;

import Modelo.Salida;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Conexion.Conexiondb;
import Modelo.DetalleSalida;

public class SalidaDaoImpl implements SalidaDAO {

    private final Connection conn;

    public SalidaDaoImpl() {
        this.conn = Conexiondb.getConexion(); // Usa tu clase de conexión
    }

 @Override
public boolean registrarSalida(Salida salida, List<DetalleSalida> detalles) {
    String sqlCabecera = "INSERT INTO salidas (fecha, subtotal, igv, total, id_usuario) VALUES (?, ?, ?, ?, ?)";
    String sqlDetalle = "INSERT INTO detalle_salida (id_salida, id_producto, cantidad, precio_venta, importe) VALUES (?, ?, ?, ?, ?)";
    String sqlActualizarStock = "UPDATE productos SET stock = stock - ? WHERE id_producto = ?";

    try (Connection conn = Conexiondb.getConexion()) {
        conn.setAutoCommit(false);

        try (PreparedStatement psCab = conn.prepareStatement(sqlCabecera, Statement.RETURN_GENERATED_KEYS)) {

            // Insertar cabecera
            psCab.setTimestamp(1, new java.sql.Timestamp(salida.getFecha().getTime()));
            psCab.setDouble(2, salida.getSubtotal());
            psCab.setDouble(3, salida.getIgv());
            psCab.setDouble(4, salida.getTotal());
            psCab.setInt(5, salida.getIdUsuario());
            psCab.executeUpdate();

            // Obtener ID generado
            ResultSet rs = psCab.getGeneratedKeys();
            int idSalida = -1;
            if (rs.next()) {
                idSalida = rs.getInt(1);
            }

            // Insertar detalle
            try (PreparedStatement psDet = conn.prepareStatement(sqlDetalle);
                 PreparedStatement psStock = conn.prepareStatement(sqlActualizarStock)) {

                for (DetalleSalida det : detalles) {
                    psDet.setInt(1, idSalida);
                    psDet.setInt(2, det.getIdProducto());
                    psDet.setInt(3, det.getCantidad());
                    psDet.setDouble(4, det.getPrecioVenta());
                    psDet.setDouble(5, det.getImporte());
                    psDet.addBatch();

                    psStock.setInt(1, det.getCantidad());
                    psStock.setInt(2, det.getIdProducto());
                    psStock.addBatch();
                }

                psDet.executeBatch();
                psStock.executeBatch();
            }

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
    public List<Salida> listarSalidas() {
        List<Salida> lista = new ArrayList<>();
        String sql = "SELECT * FROM salidas";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Salida s = new Salida();
                s.setIdSalida(rs.getInt("id_salida"));
               
                s.setFecha(rs.getTimestamp("fecha"));
                s.setIdUsuario(rs.getInt("id_usuario"));
                lista.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar salidas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Salida obtenerSalidaPorId(int idSalida) {
        String sql = "SELECT * FROM salidas WHERE id_salida = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idSalida);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Salida s = new Salida();
                    s.setIdSalida(rs.getInt("id_salida"));
                 
                    s.setFecha(rs.getTimestamp("fecha"));
                    s.setIdUsuario(rs.getInt("id_usuario"));
                    return s;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener salida: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean eliminarSalida(int idSalida) {
        String sql = "DELETE FROM salidas WHERE id_salida = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idSalida);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar salida: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarSalida(Salida salida) {
        String sql = "UPDATE salidas SET id_producto = ?, cantidad = ?, fecha = ?, id_usuario = ? WHERE id_salida = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
           
            ps.setTimestamp(3, new java.sql.Timestamp(salida.getFecha().getTime()));
            ps.setInt(4, salida.getIdUsuario());
            ps.setInt(5, salida.getIdSalida());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar salida: " + e.getMessage());
            return false;
        }
    }
}
