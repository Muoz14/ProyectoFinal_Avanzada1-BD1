package entity;

public class Usuario {
    
    private int idUsuario;
    private String nombre_empleado;
    private String apellido_empleado;
    private String user;
    private String pass;
    private String borrado;

    public Usuario() {}

    public Usuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(String nombre_empleado, String apellido_empleado, String user, String pass, String borrado) {
        this.nombre_empleado = nombre_empleado;
        this.apellido_empleado = apellido_empleado;
        this.user = user;
        this.pass = pass;
        this.borrado = borrado;
    }

    public Usuario(int idUsuario, String nombre_empleado, String apellido_empleado, String user, String pass, String borrado) {
        this.idUsuario = idUsuario;
        this.nombre_empleado = nombre_empleado;
        this.apellido_empleado = apellido_empleado;
        this.user = user;
        this.pass = pass;
        this.borrado = borrado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getBorrado() {
        return borrado;
    }

    public void setBorrado(String borrado) {
        this.borrado = borrado;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre) {
        this.nombre_empleado = nombre;
    }

    public String getApellido_empleado() {
        return apellido_empleado;
    }

    public void setApellido_empleado(String apellido) {
        this.apellido_empleado = apellido;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
            
    @Override
    public String toString() {
        return nombre_empleado + " " + apellido_empleado;
    }

}
