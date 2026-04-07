package entity;

import java.util.Date;

public class Producto {
    
    private int idProducto;
    private String nombreProducto;
    private double precio; 
    private int stockActual;
    private String descripcion;
    private String borrado;
    private Date fechaBorrado;    

    public Producto() {}

    public Producto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(String nombreProducto, double precio, int stockActual, String descripcion, String borrado, Date fechaBorrado) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.stockActual = stockActual;
        this.descripcion = descripcion;
        this.borrado = borrado;
        this.fechaBorrado = fechaBorrado;
    }

    public Producto(int idProducto, String nombreProducto, double precio, int stockActual, String descripcion, String borrado, Date fechaBorrado) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.stockActual = stockActual;
        this.descripcion = descripcion;
        this.borrado = borrado;
        this.fechaBorrado = fechaBorrado;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getBorrado() {
        return borrado;
    }

    public void setBorrado(String borrado) {
        this.borrado = borrado;
    }

    public Date getFechaBorrado() {
        return fechaBorrado;
    }

    public void setFechaBorrado(Date fechaBorrado) {
        this.fechaBorrado = fechaBorrado;
    }
    
    @Override
    public String toString() {
        return nombreProducto + " (Stock: " + stockActual + ")";
    }
    
}
