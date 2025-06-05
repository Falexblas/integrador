package DAO;

import Conexion.Conexiondb;
import Modelo.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    // 1. Registrar proveedor
    public boolean registrarProveedor(Proveedor proveedor) {
        String sql = "INSERT INTO proveedores (nombre, apellido, documento, razon_social, direccion, telefono) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexiondb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getApellido());
            stmt.setString(3, proveedor.getDocumento());
            stmt.setString(4, proveedor.getRazon_social());
            stmt.setString(5, proveedor.getDireccion());
            stmt.setString(6, proveedor.getTelefono());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al registrar proveedor.");
            e.printStackTrace();
            return false;
        }
    }

    // 2. Listar proveedores
    public List<Proveedor> listarProveedores() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedores";

        try (Connection conn = Conexiondb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId_proveedor(rs.getInt("id_proveedor"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setDocumento(rs.getString("documento"));
                p.setRazon_social(rs.getString("razon_social"));
                p.setDireccion(rs.getString("direccion"));
                p.setTelefono(rs.getString("telefono"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar proveedores.");
            e.printStackTrace();
        }
        return lista;
    }

    // 3. Actualizar proveedor
    public boolean actualizarProveedor(Proveedor proveedor) {
        String sql = "UPDATE proveedores SET nombre=?, apellido=?, documento=?, razon_social=?, direccion=?, telefono=? WHERE id_proveedor=?";
        try (Connection conn = Conexiondb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getApellido());
            stmt.setString(3, proveedor.getDocumento());
            stmt.setString(4, proveedor.getRazon_social());
            stmt.setString(5, proveedor.getDireccion());
            stmt.setString(6, proveedor.getTelefono());
            stmt.setInt(7, proveedor.getId_proveedor());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar proveedor.");
            e.printStackTrace();
            return false;
        }
    }

    // 4. Eliminar proveedor
    public boolean eliminarProveedor(int id_proveedor) {
        String sql = "DELETE FROM proveedores WHERE id_proveedor=?";
        try (Connection conn = Conexiondb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_proveedor);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar proveedor.");
            e.printStackTrace();
            return false;
        }
    }

    // 5. Buscar por documento
    public Proveedor buscarPorDocumento(String documento) {
        String sql = "SELECT * FROM proveedores WHERE documento=?";
        try (Connection conn = Conexiondb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, documento);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId_proveedor(rs.getInt("id_proveedor"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setDocumento(rs.getString("documento"));
                p.setRazon_social(rs.getString("razon_social"));
                p.setDireccion(rs.getString("direccion"));
                p.setTelefono(rs.getString("telefono"));
                return p;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar proveedor.");
            e.printStackTrace();
        }
        return null;
    }
}
