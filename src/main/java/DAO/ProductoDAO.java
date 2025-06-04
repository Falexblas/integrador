package DAO;

import Modelo.Producto;
import java.util.List;

public interface ProductoDAO {

    void insertarProducto(Producto producto);

    List<Producto> obtenerTodosLosProductos();

    Producto obtenerProductoPorId(int id);

    void actualizarProducto(Producto producto);

    void eliminarProducto(int id);
    
    List<Producto> obtenerProductosBajoStock();

}
