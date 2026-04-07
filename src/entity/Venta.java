package entity;

import java.util.Date;

public class Venta{
    
    private int noFactura;
    private Date fecha;
    private int idCliente;
    private int idUsuario;

    public Venta() {}

    public Venta(int noFactura, Date fecha, int idCliente, int idUsuario) {
        this.noFactura = noFactura;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
    }

    public int getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(int noFactura) {
        this.noFactura = noFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
