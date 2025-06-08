package Controlador;

import DAO.CategoriaDAO;
import Modelo.Categoria;
import java.util.List;

public class CategoriaControlador {

    private CategoriaDAO categoriaDAO;

    public CategoriaControlador() {
        this.categoriaDAO = new CategoriaDAO(); 
    }

    // 1. Registrar categoría
    public boolean registrarCategoria(String nombre) {
        return categoriaDAO.registrarCategoria(nombre);
    }

    // 2. Listar todas las categorías
    public List<Categoria> listarCategorias() {
        return categoriaDAO.listarCategorias();
    }

    // 3. Actualizar categoría
    public boolean actualizarCategoria(int id_categoria, String nombre) {
        Categoria categoria = new Categoria();
        categoria.setId_categoria(id_categoria);
        categoria.setNombre(nombre);
        return categoriaDAO.actualizarCategoria(categoria);
    }

    // 4. Eliminar categoría por ID
    public boolean eliminarCategoria(int id_categoria) {
        return categoriaDAO.eliminarCategoria(id_categoria);
    }
    // 6. Buscar categoría por nombre
    public Categoria getCategoriaByNombre(String nombre) {
        return categoriaDAO.getCategoriaByNombre(nombre);
    }
    
    // 7. Buscar categoría por ID
public Categoria getCategoriaById(int id_categoria) {
    return categoriaDAO.getCategoriaById(id_categoria);
}

}