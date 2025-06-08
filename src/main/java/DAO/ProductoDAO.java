package DAO;

import Modelo.Producto;
import java.util.List;

public interface ProductoDAO {

    void insertarProducto(Producto producto);

    List<Producto> obtenerTodosLosProductos();

    Producto obtenerProductoPorId(int id);

    boolean actualizarProducto(Producto producto);

    boolean eliminarProducto(int id);
    
   public boolean actualizarStock(int idProducto, int diferencia);


}
