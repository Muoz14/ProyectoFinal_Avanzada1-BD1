package dao;

import entity.Cliente;
import factory.ConexionFactory;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO {
    
    public void insertCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre_cliente, apellido_cliente, telefono, correo, direccion) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getNombreCliente());
            stmt.setString(2, cliente.getApellidoCliente());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getCorreo());
            stmt.setString(5, cliente.getDireccion());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cliente registrado correctamente!");
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar el cliente: " + e.getMessage());
        }
    }

    public List<Cliente> searchClientes() {
        List<Cliente> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM clientes WHERE borrado = ''";

        try (Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setIdCliente(rs.getInt("id_cliente"));
                cli.setNombreCliente(rs.getString("nombre_cliente"));
                cli.setApellidoCliente(rs.getString("apellido_cliente"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setCorreo(rs.getString("correo"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setBorrado(rs.getString("borrado"));
                cli.setFechaBorrado(rs.getDate("fecha_borrado")); 

                lista.add(cli);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los clientes: " + e.getMessage());
        }
        
        return lista;
    } 
    
    public void updateCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre_cliente=?, apellido_cliente=?, telefono=?, correo=?, direccion=? WHERE id_cliente=?";
        
        try (Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getNombreCliente());
            stmt.setString(2, cliente.getApellidoCliente());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getCorreo());
            stmt.setString(5, cliente.getDireccion());
            stmt.setInt(6, cliente.getIdCliente()); 
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el cliente: " + e.getMessage());
        }
    }

    public void deleteCliente(int idCliente) {
        //Al borrar, asignamos '*' y guardamos la fecha y hora exacta
        String sql = "UPDATE clientes SET borrado = '*', fecha_borrado = NOW() WHERE id_cliente = ?";
        
        try (Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al realizar el borrado lógico del cliente: " + e.getMessage());
        }
    }  
    
    public void restaurarCliente(int id) {
        String sql = "UPDATE clientes SET borrado = '', fecha_borrado = NULL WHERE id_cliente = ?";
        
        try (Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al restaurar el cliente: " + e.getMessage());
        }
    }
    
    public List<Cliente> searchClientesEliminados() {
        List<Cliente> lista = new ArrayList<>();
        
        //Solicitamos todas las columnas necesarias
        String sql = "SELECT * FROM clientes WHERE borrado != ''";
        
        try (Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombreCliente(rs.getString("nombre_cliente"));
                c.setApellidoCliente(rs.getString("apellido_cliente"));
                c.setTelefono(rs.getString("telefono"));
                c.setCorreo(rs.getString("correo"));
                c.setDireccion(rs.getString("direccion"));
                c.setBorrado(rs.getString("borrado"));
                c.setFechaBorrado(rs.getDate("fecha_borrado"));
                
                lista.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar clientes eliminados: " + e.getMessage());
        }
        return lista;
    }
    
    //========================================================
        //REPORTES 
    //=======================================================
    public List<Object[]> obtenerHistorialCompras(int idCliente) {
        
        List<Object[]> lista = new ArrayList<>();
        // Ajusté los nombres de las columnas a 'nombre_empleado' como en tus otros DAOs
        String sql = "SELECT v.no_factura, v.fecha, "
                   + "CONCAT(u.nombre_empleado, ' ', u.apellido_empleado) AS cajero, "
                   + "SUM(d.cantidad * d.precio) AS total_factura "
                   + "FROM ventas v "
                   + "INNER JOIN usuarios u ON v.id_usuario = u.id_usuario "
                   + "INNER JOIN detalle_ventas d ON v.no_factura = d.no_factura "
                   + "WHERE v.id_cliente = ? "
                   + "GROUP BY v.no_factura, v.fecha, u.nombre_empleado, u.apellido_empleado "
                   + "ORDER BY v.fecha DESC";

        try (Connection con = new ConexionFactory().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, idCliente);

                try (java.sql.ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Object[] fila = new Object[4];
                        fila[0] = rs.getInt("no_factura");

                        Timestamp ts = rs.getTimestamp("fecha");
                        fila[1] = (ts != null) ? new SimpleDateFormat("dd/MM/yyyy HH:mm").format(ts) : "N/A";

                        fila[2] = rs.getString("cajero");
                        fila[3] = rs.getDouble("total_factura");
                        lista.add(fila);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error en historial compras: " + e.getMessage());
            }
            return lista;
        }
    }
//    public static void main(String[] args) {
//        ClienteDAO dao = new ClienteDAO();
//
//        // ==========================================
//        // PROBAR INSERTAR 
//        // ==========================================
//        
////        Cliente nuevoCli = new Cliente();
////        nuevoCli.setNombreCliente("Juan");
////        nuevoCli.setApellidoCliente("Perez");
////        nuevoCli.setTelefono("555-1234");
////        nuevoCli.setCorreo("juan@ejemplo.com");
////        nuevoCli.setDireccion("Calle Falsa 123");
////        
////        dao.insertCliente(nuevoCli);
//        
//
//        // ==========================================
//        // PROBAR LISTAR 
//        // ==========================================
////        System.out.println("\n--- LISTA DE CLIENTES ACTIVOS ---");
////        List<Cliente> lista = dao.searchClientes();
////        
////        if (lista.isEmpty()) {
////            System.out.println("No hay clientes activos en la base de datos.");
////        } else {
////            for (Cliente c : lista) {
////                System.out.println("ID: " + c.getIdCliente() + 
////                                   " | Nombre: " + c.getNombreCliente() + " " + c.getApellidoCliente() + 
////                                   " | Teléfono: " + c.getTelefono());
////            }
////        }
////        System.out.println("----------------------------------\n");
//
//        // ==========================================
//        // PROBAR ACTUALIZAR 
//        // ==========================================
//        
////        Cliente cliActualizado = new Cliente();
////        cliActualizado.setIdCliente(1); // Pon un ID real de tu tabla
////        cliActualizado.setNombreCliente("Malon Carlos");
////        cliActualizado.setApellidoCliente("Perez Lopez");
////        cliActualizado.setTelefono("555-9999");
////        cliActualizado.setCorreo("jc_perez@ejemplo.com");
////        cliActualizado.setDireccion("Avenida Siempre Viva 742");
////        
////        dao.updateCliente(cliActualizado);
//        
//
//        // ==========================================
//        // PROBAR ELIMINAR LÓGICO 
//        // ==========================================
////
////        int idEliminar = 1; 
////        dao.deleteCliente(idEliminar);
//
//    }    
    

