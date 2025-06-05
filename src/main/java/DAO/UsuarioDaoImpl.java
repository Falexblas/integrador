
package DAO;

import Modelo.Usuario;
import Conexion.Conexiondb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDaoImpl implements UsuarioDAO {

    @Override
    public void registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre_usuario, password, rol) VALUES (?, ?, ?)";

        try (Connection conn = Conexiondb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getRol());

            stmt.executeUpdate();
            System.out.println("✅ Usuario registrado correctamente.");

        } catch (SQLException e) {
            System.out.println("❌ Error al registrar usuario.");
            e.printStackTrace();
        }
    }

    @Override
    public Usuario iniciarSesion(String nombreUsuario, String password) {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND password = ?";

        try (Connection conn = Conexiondb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreUsuario.trim());
            stmt.setString(2, password.trim());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRol(rs.getString("rol"));
                return usuario;
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al iniciar sesión.");
            e.printStackTrace();
        }

        return null;
    }
}
