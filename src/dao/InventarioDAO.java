package dao;

import entity.Inventario;
import factory.ConexionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InventarioDAO{
    
    //Metodo para procesar un movimiento y actualizar el stock automaticamente
    public void insertMovimiento(Inventario inv) {
        
        // SQL para guardar el historial
        String sqlHistorial = "INSERT INTO inventarios (id_producto, id_usuario, tipo_movimiento, cantidad) VALUES (?, ?, ?, ?)";
        
        // SQL para actualizar el stock dependiento si entra o sale
        String sqlActualizarStock;
        if (inv.getTipoMovimiento().equals("Entrada")) {
            sqlActualizarStock = "UPDATE productos SET stock_actual = stock_actual + ? WHERE id_producto = ?";
        } else {
            sqlActualizarStock = "UPDATE productos SET stock_actual = stock_actual - ? WHERE id_producto = ?";
        }

        try (Connection con = new ConexionFactory().getConexion()) {
            
            // Usamos un bloque "try" doble para ejecutar ambas consultas seguidas
            try (PreparedStatement stmtHistorial = con.prepareStatement(sqlHistorial);
                 PreparedStatement stmtStock = con.prepareStatement(sqlActualizarStock)) {
                
                //Guardamos el historial en la tabla inventarios
                stmtHistorial.setInt(1, inv.getIdProducto());
                stmtHistorial.setInt(2, inv.getIdUsuario());
                stmtHistorial.setString(3, inv.getTipoMovimiento());
                stmtHistorial.setInt(4, inv.getCantidad());
                stmtHistorial.executeUpdate();
                
                //Actualizamos el stock en la tabla productos
                stmtStock.setInt(1, inv.getCantidad());
                stmtStock.setInt(2, inv.getIdProducto());
                stmtStock.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Movimiento procesado y stock actualizado correctamente!");
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al procesar el inventario: " + e.getMessage());
        }
    }

    //Metodo para mostrar la tabla con INNER JOIN 
    public List<Inventario> listarMovimientos() {
        List<Inventario> lista = new ArrayList<>();
        
        // Cruzamos la tabla Inventarios con Productos y Usuarios para sacar sus nombres reales
        String sql = "SELECT i.id_movimiento, p.nombre_producto, u.nombre_empleado, i.tipo_movimiento, i.cantidad, p.stock_actual, i.fecha_movimiento " +
                     "FROM inventarios i " +
                     "INNER JOIN productos p ON i.id_producto = p.id_producto " +
                     "INNER JOIN usuarios u ON i.id_usuario = u.id_usuario " +
                     "ORDER BY i.id_movimiento DESC"; 

        try (Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Inventario inv = new Inventario();
                
                inv.setIdMovimiento(rs.getInt("id_movimiento"));
                
                inv.setNombreProducto(rs.getString("nombre_producto")); 
                
                inv.setNombreUsuario(rs.getString("nombre_empleado"));  
                
                inv.setTipoMovimiento(rs.getString("tipo_movimiento"));
                
                inv.setCantidad(rs.getInt("cantidad"));
                
                inv.setStockActualProducto(rs.getInt("stock_actual"));
                
                inv.setFechaMovimiento(rs.getTimestamp("fecha_movimiento"));
                
                lista.add(inv);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los movimientos: " + e.getMessage());
        }
        
        return lista;
    }  
    
}
