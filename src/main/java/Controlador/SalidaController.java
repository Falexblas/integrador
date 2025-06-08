package Controlador;

import DAO.SalidaDAO;
import DAO.SalidaDaoImpl;
import Modelo.DetalleSalida;
import Modelo.Salida;
import java.util.List;

public class SalidaController {

    private final SalidaDAO salidaDAO;

    public SalidaController() {
        this.salidaDAO = new SalidaDaoImpl();
    }

    
    public boolean registrarSalida(Salida salida, List<DetalleSalida> detalles) {
        return salidaDAO.registrarSalida(salida, detalles);
    }

    // Listar todas las salidas
    public List<Salida> listarSalidas() {
        return salidaDAO.listarSalidas();
    }

    // Obtener una salida específica por ID
    public Salida obtenerSalidaPorId(int idSalida) {
        return salidaDAO.obtenerSalidaPorId(idSalida);
    }

    // Eliminar una salida
    public boolean eliminarSalida(int idSalida) {
        return salidaDAO.eliminarSalida(idSalida);
    }

    // Actualizar salida (si permites editar salidas)
    public boolean actualizarSalida(Salida salida) {
        return salidaDAO.actualizarSalida(salida);
    }
}
