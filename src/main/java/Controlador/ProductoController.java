package Controlador;

import DAO.ProductoDAO;
import DAO.ProductoDaoImpl;
import Modelo.Producto;

import java.util.List;

public class ProductoController {

    private final ProductoDAO productoDAO;

    public ProductoController() {
        this.productoDAO = new ProductoDaoImpl();
    }

    public void insertarProducto(Producto producto) {
        productoDAO.insertarProducto(producto);
    }

    public List<Producto> listarProductos() {
        return productoDAO.obtenerTodosLosProductos();
    }

    public Producto obtenerProductoPorId(int id) {
        return productoDAO.obtenerProductoPorId(id);
    }

    public boolean actualizarProducto(Producto producto) {
    return productoDAO.actualizarProducto(producto);
}


    public boolean eliminarProducto(int id) {
        return productoDAO.eliminarProducto(id);
    }
     // Método para actualizar el stock sumando la diferencia
    public boolean actualizarStockProducto(int idProducto, int diferencia) {
        return productoDAO.actualizarStock(idProducto, diferencia);
    }
}
