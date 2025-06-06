package Modelo;

public class Categoria {
    private int id_categoria;
    private String nombre;

    // Getters y Setters
    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    
    public String getNombre() {
        return nombre;
    }

    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return nombre; // Para mostrar solo el nombre en JComboBox, JList, etc.
    }
}