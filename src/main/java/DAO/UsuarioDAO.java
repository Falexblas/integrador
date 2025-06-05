
package DAO;

import Modelo.Usuario;


public interface UsuarioDAO {
    void registrarUsuario(Usuario usuario);
    Usuario iniciarSesion(String nombreUsuario, String password);
}
