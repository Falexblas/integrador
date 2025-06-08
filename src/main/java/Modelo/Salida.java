package Modelo;

import java.util.Date;
import java.util.List;

public class Salida {

    private int idSalida;
    private Date fecha;
    private double subtotal;
    private double igv;
    private double total;
    private int idUsuario;
    private List<DetalleSalida> detalles;

    public Salida() {
    }

    public Salida(int idSalida, Date fecha, double subtotal, double igv, double total, int idUsuario, List<DetalleSalida> detalles) {
        this.idSalida = idSalida;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
        this.idUsuario = idUsuario;
        this.detalles = detalles;
    }

    public int getIdSalida() {
        return idSalida;
    }

    public void setIdSalida(int idSalida) {
        this.idSalida = idSalida;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<DetalleSalida> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleSalida> detalles) {
        this.detalles = detalles;
    }
}
