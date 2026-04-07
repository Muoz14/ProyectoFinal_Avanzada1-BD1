package entity;

import java.util.Date;

public class Cliente {
    
    private int idCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String telefono;
    private String correo;
    private String direccion;
    private String borrado;
    private Date fechaBorrado; 

    public Cliente() {}

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(String nombreCliente, String apellidoCliente, String telefono, String correo, String direccion, String borrado, Date fechaBorrado) {
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.borrado = borrado;
        this.fechaBorrado = fechaBorrado;
    }

    public Cliente(int idCliente, String nombreCliente, String apellidoCliente, String telefono, String correo, String direccion, String borrado, Date fechaBorrado) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.borrado = borrado;
        this.fechaBorrado = fechaBorrado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
        return nombreCliente + " " + apellidoCliente; 
    }
    
}
