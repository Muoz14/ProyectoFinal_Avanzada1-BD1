package entity;

import java.util.Date;

public class Inventario{
    
    private int idMovimiento, idProducto, idUsuario;
    private String tipoMovimiento;
    private int cantidad;
    private Date fechaMovimiento;
    
    private String nombreProducto;
    private String nombreUsuario;
    private int stockActualProducto;

    public Inventario() {}

    public Inventario(int idMovimiento, int idProducto, int idUsuario) {
        this.idMovimiento = idMovimiento;
        this.idProducto = idProducto;
        this.idUsuario = idUsuario;
    }

    public Inventario(String tipoMovimiento, int cantidad, Date fechaMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
    }

    public Inventario(String nombreProducto, String nombreUsuario, int stockActualProducto) {
        this.nombreProducto = nombreProducto;
        this.nombreUsuario = nombreUsuario;
        this.stockActualProducto = stockActualProducto;
    }

    public Inventario(int idMovimiento, int idProducto, int idUsuario, String tipoMovimiento, int cantidad, Date fechaMovimiento, String nombreProducto, String nombreUsuario, int stockActualProducto) {
        this.idMovimiento = idMovimiento;
        this.idProducto = idProducto;
        this.idUsuario = idUsuario;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
        this.nombreProducto = nombreProducto;
        this.nombreUsuario = nombreUsuario;
        this.stockActualProducto = stockActualProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public int getStockActualProducto() {
        return stockActualProducto;
    }

    public void setStockActualProducto(int stockActualProducto) {
        this.stockActualProducto = stockActualProducto;
    }
}
