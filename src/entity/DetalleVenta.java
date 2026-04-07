package entity;

public class DetalleVenta{
    
    private int idDetalle;
    private int noFactura;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;
    
    // Variables auxiliares para mostrar en la tabla visual
    private String nombreProducto;
    private double subTotal;

    public DetalleVenta() {}

    public DetalleVenta(int idDetalle, int noFactura, int idProducto, int cantidad, double precioUnitario, String nombreProducto, double subTotal) {
        this.idDetalle = idDetalle;
        this.noFactura = noFactura;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.nombreProducto = nombreProducto;
        this.subTotal = subTotal;
    }
    
    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(int noFactura) {
        this.noFactura = noFactura;
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

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
 
}
