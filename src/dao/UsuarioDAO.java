package dao;

import entity.Usuario;
import factory.ConexionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDAO{
    
    public void insertUsuario(Usuario usuario){

        String sql = "INSERT INTO usuarios (nombre_empleado, apellido_empleado, user, pass) VALUES (?, ?, ?, ?)";
        
        try(Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setString(1, usuario.getNombre_empleado());
            stmt.setString(2, usuario.getApellido_empleado());
            stmt.setString(3, usuario.getUser());
            stmt.setString(4, usuario.getPass());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Usuario ingresado correctamente!");
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar el usuario" + e.getMessage());
        }
    }    
    
    public List<Usuario> searchUsuarios(){
        List<Usuario> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM usuarios WHERE borrado = ''";

        try(Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()){
                Usuario usu = new Usuario();
                usu.setIdUsuario(rs.getInt("id_usuario"));
                usu.setNombre_empleado(rs.getString("nombre_empleado"));
                usu.setApellido_empleado(rs.getString("apellido_empleado"));
                usu.setUser(rs.getString("user"));
                usu.setPass(rs.getString("pass")); 
                usu.setBorrado(rs.getString("borrado"));
                
                lista.add(usu);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los usuarios activos", e);
        }
        
        return lista;
    }

    public void updateUsuario(Usuario usuario){
        String sql = "UPDATE usuarios SET nombre_empleado=?, apellido_empleado=?, user=?, pass=? WHERE id_usuario=?";
        
        try(Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setString(1, usuario.getNombre_empleado());
            stmt.setString(2, usuario.getApellido_empleado());
            stmt.setString(3, usuario.getUser());
            stmt.setString(4, usuario.getPass());
            stmt.setInt(5, usuario.getIdUsuario()); 
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el usuario", e);
        }
    }

    public void deleteUsuario(int idUsuario){
        String sql = "UPDATE usuarios SET borrado = '*' WHERE id_usuario = ?";
        
        try (Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
            
        } catch (SQLException e){
            throw new RuntimeException("Error al realizar el borrado lógico", e);
        }
    }
    
    // Metodo para restaurar un usuario eliminado (Quitar el asterisco)
    public void restaurarUsuario(int idUsuario) {
        String sql = "UPDATE usuarios SET borrado = '' WHERE id_usuario = ?";
        
        try (Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al restaurar el usuario: " + e.getMessage());
        }
    }
    public boolean validarLogin(String user, String pass){
        // Agregamos AND borrado='' para asegurar que solo entren usuarios activos
        String sql = "SELECT id_usuario FROM usuarios WHERE user=? AND pass=? AND borrado=''";
        
        try(Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setString(1, user);
            stmt.setString(2, pass);
            
            try (ResultSet rs = stmt.executeQuery()){
                // rs.next() sera true si las credenciales son correctas y no está borrado
                return rs.next(); 
            }
            
        } catch (SQLException e){
            throw new RuntimeException("Error al validar el login: " + e.getMessage());
        }
    }    
    
    public boolean existeUsuario(String user) {
        String sql = "SELECT id_usuario FROM usuarios WHERE user = ? AND borrado = ''";
        try (Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, user);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Retorna true si encontró al menos un registro igual
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar existencia del usuario: " + e.getMessage());
        }
    }
    
    // Metodo para traer SOLO a los usuarios eliminados
    public List<Usuario> searchUsuariosEliminados() {
        List<Usuario> lista = new ArrayList<>();
        
        // Buscamos los que NO tengan el campo borrado vacío
        String sql = "SELECT * FROM usuarios WHERE borrado != ''";

        try (Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setIdUsuario(rs.getInt("id_usuario"));
                usu.setNombre_empleado(rs.getString("nombre_empleado"));
                usu.setApellido_empleado(rs.getString("apellido_empleado"));
                usu.setUser(rs.getString("user"));
                usu.setPass(rs.getString("pass")); 
                usu.setBorrado(rs.getString("borrado"));
                
                lista.add(usu);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los usuarios eliminados: " + e.getMessage());
        }
        
        return lista;
    }

    
//    public static void main(String[] args) {
//        UsuarioDAO dao = new UsuarioDAO();
//        
//        int idAEliminar = 2; 
//        
//        System.out.println("Ejecutando borrado lógico para el usuario con ID: " + idAEliminar);
//        
//        dao.deleteUsuario(idAEliminar);
//        
//        System.out.println("Borrado completado. Listando usuarios activos restantes:\n");
//        
//        List<Usuario> listaActivos = dao.searchUsuarios();
//        
//        if (listaActivos.isEmpty()) {
//            System.out.println("No hay usuarios activos registrados.");
//        } else {
//            for (Usuario u : listaActivos) {
//                System.out.println("ID: " + u.getIdUsuario() + 
//                                   " | Nombre: " + u.getNombre() + " " + u.getApellido() + 
//                                   " | User: " + u.getUser() + 
//                                   " | Password: " + u.getPass());
//            }
//        }     
//
//    }
//    
}
