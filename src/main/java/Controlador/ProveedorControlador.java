package Controlador;

import DAO.ProveedorDAO;
import Modelo.Proveedor;
import java.util.List;

public class ProveedorControlador {
    private ProveedorDAO dao = new ProveedorDAO();

    public boolean registrarProveedor(Proveedor p) {
        return dao.registrarProveedor(p);
    }

    public List<Proveedor> listarProveedores() {
        return dao.listarProveedores();
    }

    public boolean actualizarProveedor(Proveedor p) {
        return dao.actualizarProveedor(p);
    }

    public boolean eliminarProveedor(int id) {
        return dao.eliminarProveedor(id);
    }
}
