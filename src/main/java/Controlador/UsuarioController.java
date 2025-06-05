package Controlador;

import DAO.UsuarioDAO;
import DAO.UsuarioDaoImpl;
import Modelo.Usuario;

public class UsuarioController {
    private UsuarioDAO usuarioDao;

    public UsuarioController() {
        this.usuarioDao = new UsuarioDaoImpl();
    }

    public void registrarUsuario(String nombreUsuario, String password, String rol) {
        Usuario usuario = new Usuario(nombreUsuario, password, rol);
        usuarioDao.registrarUsuario(usuario);
    }

    public Usuario iniciarSesion(String nombreUsuario, String password) {
        return usuarioDao.iniciarSesion(nombreUsuario, password);
    }
}

