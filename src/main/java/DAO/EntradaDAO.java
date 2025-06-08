package DAO;

import Modelo.Entrada;
import java.util.List;

public interface EntradaDAO {

    boolean registrarEntrada(Entrada entrada);

    boolean actualizarEntrada(Entrada entrada);

    boolean eliminarEntrada(int idEntrada);

    List<Entrada> listarEntradas();

    Entrada obtenerEntradaPorId(int idEntrada);

}
