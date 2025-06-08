package Controlador;

import DAO.EntradaDAO;
import DAO.EntradaDaoImpl;
import Modelo.Entrada;
import java.util.List;

public class EntradaControlador {

    private final EntradaDAO entradaDAO;

    public EntradaControlador() {
        this.entradaDAO = new EntradaDaoImpl();
    }

    // Registrar una nueva entrada
    public boolean registrarEntrada(Entrada entrada) {
        return entradaDAO.registrarEntrada(entrada);
    }

    // Actualizar una entrada existente
    public boolean actualizarEntrada(Entrada entrada) {
        return entradaDAO.actualizarEntrada(entrada);
    }

    // Eliminar una entrada por su ID
    public boolean eliminarEntrada(int idEntrada) {
        return entradaDAO.eliminarEntrada(idEntrada);
    }

    // Obtener todas las entradas
    public List<Entrada> listarEntradas() {
        return entradaDAO.listarEntradas();
    }

    // Obtener una entrada específica por su ID
    public Entrada obtenerEntradaPorId(int idEntrada) {
        return entradaDAO.obtenerEntradaPorId(idEntrada);
    }
        public int getStockById(int idEntrada) {
    Entrada entrada = entradaDAO.obtenerEntradaPorId(idEntrada);
    if (entrada != null) {
        return entrada.getCantidad();
    }
    return 0; // O lanzar excepción si prefieres
}
    
}
