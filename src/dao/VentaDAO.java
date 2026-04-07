package dao;

import entity.DetalleVenta;
import entity.Venta;
import factory.ConexionFactory;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO{
    
    //Metodo maestro que recibe 1 Venta (encabezado) y una Lista de Detalles (el carrito)
    public boolean registrarVenta(Venta venta, List<DetalleVenta> listaDetalles) {
        
        // Consultas SQL
        String sqlVenta = "INSERT INTO ventas (id_cliente, id_usuario) VALUES (?, ?)";
        
        String sqlDetalle = "INSERT INTO detalle_ventas (no_factura, id_producto, cantidad, precio) VALUES (?, ?, ?, ?)"; 
        
        String sqlStock = "UPDATE productos SET stock_actual = stock_actual - ? WHERE id_producto = ?";
        
        String sqlHistorial = "INSERT INTO inventarios (id_producto, id_usuario, tipo_movimiento, cantidad) VALUES (?, ?, 'Salida', ?)";

        Connection con = null;

        try {
            con = new ConexionFactory().getConexion();
            con.setAutoCommit(false); // Iniciamos transaccion

            int noFacturaGenerado = 0;
            
            //GUARDAR LA VENTA (Encabezado)
            try (PreparedStatement stmtVenta = con.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
                stmtVenta.setInt(1, venta.getIdCliente());
                stmtVenta.setInt(2, venta.getIdUsuario());
                stmtVenta.executeUpdate();

                try (ResultSet rs = stmtVenta.getGeneratedKeys()) {
                    if (rs.next()) {
                        noFacturaGenerado = rs.getInt(1);
                    }
                }
            }

            //GUARDAR DETALLES, REBAJAR STOCK Y REGISTRAR EN INVENTARIO
            // Preparamos los 3 statements al mismo tiempo
            try (PreparedStatement stmtDetalle = con.prepareStatement(sqlDetalle);
                 PreparedStatement stmtStock = con.prepareStatement(sqlStock);
                 PreparedStatement stmtHistorial = con.prepareStatement(sqlHistorial)) {
                
                for (DetalleVenta detalle : listaDetalles) {
                    
                    //Insertar en detalle_ventas
                    stmtDetalle.setInt(1, noFacturaGenerado); 
                    stmtDetalle.setInt(2, detalle.getIdProducto());
                    stmtDetalle.setInt(3, detalle.getCantidad());
                    stmtDetalle.setDouble(4, detalle.getPrecioUnitario()); 
                    stmtDetalle.executeUpdate();

                    //Rebajar el stock del producto
                    stmtStock.setInt(1, detalle.getCantidad());
                    stmtStock.setInt(2, detalle.getIdProducto());
                    stmtStock.executeUpdate();
                    
                    //Registrar el movimiento
                    stmtHistorial.setInt(1, detalle.getIdProducto());
                    stmtHistorial.setInt(2, venta.getIdUsuario()); // El cajero que hace la venta
                    stmtHistorial.setInt(3, detalle.getCantidad()); // Cantidad que sale
                    stmtHistorial.executeUpdate();
                }
            }

            con.commit(); // Confirmar todo
            return true;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); 
                } catch (SQLException ex) {
                    System.out.println("Error en rollback: " + ex.getMessage());
                }
            }
            throw new RuntimeException("Error al procesar la venta: " + e.getMessage());
            
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar conexión: " + e.getMessage());
                }
            }
        }
    }   
    
    //========================================================
    //REPORTES A CONTINUACION   
    //========================================================
    //REPORTE DE VENTAS POR RANGO DE FECHAS
    public java.util.List<Object[]> reporteVentasPorFecha(java.util.Date fechaInicio, java.util.Date fechaFin) {
        
        java.util.List<Object[]> lista = new java.util.ArrayList<>();
        
        // Volvemos a tu consulta SQL original que SÍ coincide con las columnas de tu base de datos
        String sql = "SELECT v.no_factura, v.fecha, "
                   + "CONCAT(c.nombre_cliente, ' ', c.apellido_cliente) AS nombre_cliente, "
                   + "CONCAT(u.nombre_empleado, ' ', u.apellido_empleado) AS nombre_empleado, "
                   + "SUM(d.cantidad * d.precio) AS total_factura "
                   + "FROM ventas v "
                   + "INNER JOIN clientes c ON v.id_cliente = c.id_cliente "
                   + "INNER JOIN usuarios u ON v.id_usuario = u.id_usuario "
                   + "INNER JOIN detalle_ventas d ON v.no_factura = d.no_factura "
                   + "WHERE DATE(v.fecha) BETWEEN ? AND ? "
                   + "GROUP BY v.no_factura, v.fecha, c.nombre_cliente, c.apellido_cliente, u.nombre_empleado, u.apellido_empleado "
                   + "ORDER BY v.fecha DESC";

        try (java.sql.Connection con = new factory.ConexionFactory().getConexion();
             java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            
            // AQUI LA CORRECCIÓN CLAVE: Convertimos las fechas util a sql
            ps.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            ps.setDate(2, new java.sql.Date(fechaFin.getTime()));
            
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] fila = new Object[5];
                    fila[0] = rs.getInt("no_factura");
                    
                    // Extraemos tu columna 'fecha' original
                    java.sql.Timestamp fechaSQL = rs.getTimestamp("fecha");
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
                    fila[1] = (fechaSQL != null) ? sdf.format(fechaSQL) : "N/A";
                    
                    fila[2] = rs.getString("nombre_cliente");
                    fila[3] = rs.getString("nombre_empleado");
                    fila[4] = rs.getDouble("total_factura");
                    
                    lista.add(fila);
                }
            }
        } catch (Exception e) {
            System.out.println("Error en reporteVentasPorFecha: " + e.getMessage());
            e.printStackTrace(); // Esto nos mostrará en consola si hay algún otro fallo
        }
        return lista;
    }
    
    // METODOS PARA EL DASHBOARD: Estadísticas de Ventas
    //Obtener la suma de TODAS las ventas del dia actual
    public double obtenerTotalVentasHoy() {
        double total = 0.0;
        String sql = "SELECT COALESCE(SUM(d.cantidad * d.precio), 0) FROM ventas v INNER JOIN detalle_ventas d ON v.no_factura = d.no_factura WHERE DATE(v.fecha) = CURDATE()";
        
        try (java.sql.Connection con = new factory.ConexionFactory().getConexion();
             java.sql.PreparedStatement ps = con.prepareStatement(sql);
             java.sql.ResultSet rs = ps.executeQuery()) {
            if (rs.next()) total = rs.getDouble(1);
        } catch (Exception e) { System.out.println("Error Ventas Hoy: " + e.getMessage()); }
        return total;
    }

    //Obtener el valor de la factura más grande del día actual
    public double obtenerVentaMaximaHoy() {
        double maxima = 0.0;
        String sql = "SELECT COALESCE(MAX(total_factura), 0) FROM (SELECT SUM(d.cantidad * d.precio) AS total_factura FROM ventas v INNER JOIN detalle_ventas d ON v.no_factura = d.no_factura WHERE DATE(v.fecha) = CURDATE() GROUP BY v.no_factura) AS subquery";
        
        try (java.sql.Connection con = new factory.ConexionFactory().getConexion();
             java.sql.PreparedStatement ps = con.prepareStatement(sql);
             java.sql.ResultSet rs = ps.executeQuery()) {
            if (rs.next()) maxima = rs.getDouble(1);
        } catch (Exception e) { System.out.println("Error Venta Máxima: " + e.getMessage()); }
        return maxima;
    }

    //Obtener la suma de ventas de TODO el mes actual
    public double obtenerVentasMesActual() {
        double totalMes = 0.0;
        String sql = "SELECT COALESCE(SUM(d.cantidad * d.precio), 0) FROM ventas v INNER JOIN detalle_ventas d ON v.no_factura = d.no_factura WHERE MONTH(v.fecha) = MONTH(CURDATE()) AND YEAR(v.fecha) = YEAR(CURDATE())";
        
        try (java.sql.Connection con = new factory.ConexionFactory().getConexion();
             java.sql.PreparedStatement ps = con.prepareStatement(sql);
             java.sql.ResultSet rs = ps.executeQuery()) {
            if (rs.next()) totalMes = rs.getDouble(1);
        } catch (Exception e) { System.out.println("Error Ventas Mes: " + e.getMessage()); }
        return totalMes;
    }

    //Obtener ventas de los ultimos 6 meses para la Grafica
    public java.util.List<Object[]> obtenerVentasUltimos6Meses() {
        java.util.List<Object[]> lista = new java.util.ArrayList<>();
        String sql = "SELECT MONTH(v.fecha) AS mes, SUM(d.cantidad * d.precio) AS total "
                   + "FROM ventas v INNER JOIN detalle_ventas d ON v.no_factura = d.no_factura "
                   + "WHERE v.fecha >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH) "
                   + "GROUP BY YEAR(v.fecha), MONTH(v.fecha) "
                   + "ORDER BY YEAR(v.fecha) ASC, MONTH(v.fecha) ASC";

        try (java.sql.Connection con = new factory.ConexionFactory().getConexion();
             java.sql.PreparedStatement ps = con.prepareStatement(sql);
             java.sql.ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Object[] fila = new Object[2];
                fila[0] = rs.getInt("mes"); // Numero del mes (1-12)
                fila[1] = rs.getDouble("total"); // Dinero
                lista.add(fila);
            }
        } catch (Exception e) { System.out.println("Error Gráfica: " + e.getMessage()); }
        return lista;
    }
    
}
