package Modelo;

public class DetalleSalida {
    private int idProducto;
    private int cantidad;
    private double precioVenta;
    private double importe;

    public DetalleSalida() {
    }

    public DetalleSalida(int idProducto, int cantidad, double precioVenta, double importe) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.importe = importe;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
}
