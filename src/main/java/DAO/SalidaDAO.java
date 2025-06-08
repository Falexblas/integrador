package DAO;

import Modelo.DetalleSalida;
import Modelo.Salida;
import java.util.List;

public interface SalidaDAO {

    // Registrar una nueva salida
    boolean registrarSalida(Salida salida, List<DetalleSalida> detalles);

    // Listar todas las salidas
    List<Salida> listarSalidas();

    // Obtener una salida por su ID
    Salida obtenerSalidaPorId(int idSalida);

    // Eliminar una salida
    boolean eliminarSalida(int idSalida);

    // Editar una salida (opcional, solo si permites modificar salidas)
    boolean actualizarSalida(Salida salida);
}
