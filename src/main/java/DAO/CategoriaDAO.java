package DAO;

import Conexion.Conexiondb;
import Modelo.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    // 1. Método para registrar una nueva categoría
    public boolean registrarCategoria(String nombre) {
        String sql = "INSERT INTO categorias (nombre) VALUES (?)";
        try (Connection conn = Conexiondb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al registrar categoría.");
            e.printStackTrace();
            return false;
        }
    }

    // 2. Método para listar todas las categorías
    public List<Categoria> listarCategorias() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias";

        try (Connection conn = Conexiondb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId_categoria(rs.getInt("id_categoria"));
                c.setNombre(rs.getString("nombre"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener categorías.");
            e.printStackTrace();
        }

        return lista;
    }

    // 3. Método para actualizar una categoría
    public boolean actualizarCategoria(Categoria categoria) {
        String sql = "UPDATE categorias SET nombre = ? WHERE id_categoria = ?";
        try (Connection conn = Conexiondb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNombre());
            stmt.setInt(2, categoria.getId_categoria());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar categoría.");
            e.printStackTrace();
            return false;
        }
    }

    // 4. Método para eliminar una categoría por ID
    public boolean eliminarCategoria(int id_categoria) {
        String sql = "DELETE FROM categorias WHERE id_categoria = ?";
        try (Connection conn = Conexiondb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_categoria);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar categoría.");
            e.printStackTrace();
            return false;
        }
    }

    // 5. Método para buscar una categoría por nombre
    public Categoria getCategoriaByNombre(String nombre) {
        String sql = "SELECT * FROM categorias WHERE nombre = ?";
        try (Connection conn = Conexiondb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Categoria c = new Categoria();
                c.setId_categoria(rs.getInt("id_categoria"));
                c.setNombre(rs.getString("nombre"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al buscar categoría por nombre.");
            e.printStackTrace();
        }
        return null;
    }
}