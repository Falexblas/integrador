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

    public void actualizarProducto(Producto producto) {
        productoDAO.actualizarProducto(producto);
    }

    public void eliminarProducto(int id) {
        productoDAO.eliminarProducto(id);
       
    }
    
    public List<Producto> listarProductosBajoStock() {
       return productoDAO.obtenerProductosBajoStock();
        
    }

}
