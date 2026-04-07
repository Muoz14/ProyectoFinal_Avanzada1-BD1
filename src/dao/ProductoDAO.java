package dao;

import entity.Producto;
import factory.ConexionFactory;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductoDAO{
    
    public void insertProducto(Producto producto){
        
        String sql = "INSERT INTO productos (nombre_producto, precio_unitario, stock_actual, descripcion) VALUES (?, ?, ?, ?)";
        
        try(Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, producto.getNombreProducto());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getStockActual());
            stmt.setString(4, producto.getDescripcion());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Producto registrado correctamente!");
            
        }catch(SQLException e){
            throw new RuntimeException("Error al registrar el producto: " + e.getMessage());
        }
    } 
    
    public List<Producto> searchProductos(){
        
        List<Producto> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM productos WHERE borrado = ''";

        try(Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                Producto prod = new Producto();
                prod.setIdProducto(rs.getInt("id_producto"));
                prod.setNombreProducto(rs.getString("nombre_producto"));
                prod.setPrecio(rs.getDouble("precio_unitario"));
                prod.setStockActual(rs.getInt("stock_actual"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setBorrado(rs.getString("borrado"));
                prod.setFechaBorrado(rs.getDate("fecha_borrado")); 

                lista.add(prod);
            }

        }catch(SQLException e){
            throw new RuntimeException("Error al listar los productos: " + e.getMessage());
        }
        
        return lista;
    }   

    public void updateProducto(Producto producto){
        String sql = "UPDATE productos SET nombre_producto=?, precio_unitario=?, stock_actual=?, descripcion=? WHERE id_producto=?";
        
        try(Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setString(1, producto.getNombreProducto());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getStockActual());
            stmt.setString(4, producto.getDescripcion());
            stmt.setInt(5, producto.getIdProducto()); 
            
            stmt.executeUpdate();
            
        }catch(SQLException e){
            throw new RuntimeException("Error al actualizar el producto: " + e.getMessage());
        }
    }

    public void deleteProducto(int idProducto){
        // Asignamos el '*' al campo borrado y usamos NOW() para la fecha exacta del servidor
        String sql = "UPDATE productos SET borrado = '*', fecha_borrado = NOW() WHERE id_producto = ?";
        
        try(Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setInt(1, idProducto);
            stmt.executeUpdate();
            
        }catch(SQLException e){
            throw new RuntimeException("Error al realizar el borrado lógico del producto: " + e.getMessage());
        }
    }  
    
    // Metodo para restaurar un producto eliminado (Quitar el asterisco)
    public void restaurarProducto(int id) {
        String sql = "UPDATE productos SET borrado = '' WHERE id_producto = ?";
        
        try (Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            
        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error al restaurar el producto: " + e.getMessage());
        }
    }
    
    // Metodo para traer SOLO a los productos eliminados
    public List<Producto> searchProductosEliminados() {
        List<Producto> lista = new ArrayList<>();
        
        String sql = "SELECT id_producto, nombre_producto, descripcion, precio_unitario, stock_actual FROM productos WHERE borrado != ''";
        
        try (Connection con = new ConexionFactory().getConexion();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto")); 
                p.setNombreProducto(rs.getString("nombre_producto"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getDouble("precio_unitario"));
                p.setStockActual(rs.getInt("stock_actual"));
                
                lista.add(p);
            }
        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Error al buscar productos eliminados: " + e.getMessage());
        }
        return lista;
    }
    
    //========================================================
        //REPORTES A CONTINUACION   
    //=======================================================
    //METODO PARA EL REPORTE DE BAJO STOCK
    public List<Producto> reporteBajoStock(int limite) {
        
        List<entity.Producto> lista = new ArrayList<>();
        
        // Ordenamos de menor a mayor para que los mas urgentes salgan arriba
        String sql = "SELECT * FROM productos WHERE stock_actual <= ? ORDER BY stock_actual ASC";
        
        try (Connection con = new ConexionFactory().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, limite); // Aqui le inyectamos el numero del JSpinner
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto p = new Producto();
                    p.setIdProducto(rs.getInt("id_producto"));
                    p.setNombreProducto(rs.getString("nombre_producto"));
                    p.setPrecio(rs.getDouble("precio_unitario"));
                    p.setStockActual(rs.getInt("stock_actual"));
                    lista.add(p);
                }
            }
        } catch (Exception e) {
            System.out.println("Error en reporteBajoStock: " + e.getMessage());
        }
        return lista;
    }
    
    //HISTORIAL DE KARDEX (SALIDAS POR VENTA)
    public List<Object[]> historialKardexProducto(int idProducto) {
        
        List<Object[]> lista = new ArrayList<>();

        // Usamos UNION ALL para juntar Ventas e Inventario
        // Importante: Usamos 'fecha' como alias global para que el ORDER BY funcione
        String sql = 
            //LAS VENTAS (SALIDAS)
            "SELECT v.fecha AS fecha, v.no_factura AS referencia, 'VENTA (Salida)' AS movimiento, d.cantidad, "
            + "CONCAT(u.nombre_empleado, ' ', u.apellido_empleado) AS cajero "
            + "FROM detalle_ventas d "
            + "INNER JOIN ventas v ON d.no_factura = v.no_factura "
            + "INNER JOIN usuarios u ON v.id_usuario = u.id_usuario "
            + "WHERE d.id_producto = ? "
            + "UNION ALL "
            //EL INVENTARIO MANUAL (ENTRADAS/SALIDAS PROCESADAS)
            + "SELECT i.fecha_movimiento AS fecha, 'N/A' AS referencia, "
            + "CONCAT('MANUAL (', i.tipo_movimiento, ')') AS movimiento, i.cantidad, "
            + "CONCAT(u.nombre_empleado, ' ', u.apellido_empleado) AS cajero "
            + "FROM inventarios i "
            + "INNER JOIN usuarios u ON i.id_usuario = u.id_usuario "
            + "WHERE i.id_producto = ? "
            + "ORDER BY fecha DESC"; 

        try (Connection con = new ConexionFactory().getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProducto);
            ps.setInt(2, idProducto);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] fila = new Object[5];

                    // Usamos el alias "fecha" que definimos arriba
                    Timestamp fechaSQL = rs.getTimestamp("fecha");
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    fila[0] = (fechaSQL != null) ? sdf.format(fechaSQL) : "N/A";

                    fila[1] = rs.getString("referencia");
                    fila[2] = rs.getString("movimiento");
                    fila[3] = rs.getInt("cantidad");
                    fila[4] = rs.getString("cajero");

                    lista.add(fila);
                }
            }
        } catch (Exception e) {
            // Imprime el error completo en consola por si falta algún nombre de columna
            System.out.println("Error en Kardex Integral: " + e.getMessage());
            e.printStackTrace(); 
        }
        return lista;
        
    }
    
    // METODO PARA EL DASHBOARD: Contar productos con bajo stock
    public int contarProductosBajoStock() {
        int cantidad = 0;
        String sql = "SELECT COUNT(*) FROM productos WHERE stock_actual <= 5"; 
        
        try (Connection con = new ConexionFactory().getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                cantidad = rs.getInt(1); // Obtiene el resultado del COUNT
            }
            
        } catch (SQLException e) {
            System.out.println("Error al contar productos bajo stock: " + e.getMessage());
        }
        return cantidad;
    }
    
//    public static void main(String[] args) {
//        ProductoDAO dao = new ProductoDAO();
//
//        // ==========================================
//        // PROBAR INSERTAR 
//        // ==========================================
//        
////        Producto nuevoProd = new Producto();
////        nuevoProd.setNombreProducto("Laptop Dell Inspiron");
////        nuevoProd.setPrecio(15000.50);
////        nuevoProd.setStockActual(10);
////        nuevoProd.setDescripcion("Laptop para programar con 16GB RAM");
////        
////        dao.insertProducto(nuevoProd);
////        System.out.println("Producto insertado");
//        
//
//        // ==========================================
//        // PROBAR LISTAR 
//        // ==========================================
//        System.out.println("\n--- LISTA DE PRODUCTOS ACTIVOS ---");
//        List<Producto> lista = dao.searchProductos();
//        
//        if (lista.isEmpty()) {
//            System.out.println("No hay productos activos en la base de datos.");
//        } else {
//            for (Producto p : lista) {
//                System.out.println("ID: " + p.getIdProducto() + 
//                                   " | Nombre: " + p.getNombreProducto() + 
//                                   " | Precio: $" + p.getPrecio() + 
//                                   " | Stock: " + p.getStockActual());
//            }
//        }
//        System.out.println("----------------------------------\n");
//
//        // ==========================================
//        // PROBAR ACTUALIZAR 
//        // ==========================================
//        
////        Producto prodActualizado = new Producto();
////        prodActualizado.setIdProducto(1); // Pon un ID real de tu tabla
////        prodActualizado.setNombreProducto("Laptop Dell XPS");
////        prodActualizado.setPrecio(25000.00);
////        prodActualizado.setStockActual(15);
////        prodActualizado.setDescripcion("Laptop de gama alta");
////        
////        dao.updateProducto(prodActualizado);
////        System.out.println("Paso 3: Producto actualizado");
//        
//
//        // ==========================================
//        // PROBAR ELIMINAR LÓGICO 
//        // ==========================================
//
////        int idEliminar = 1; 
////        dao.deleteProducto(idEliminar);
////        System.out.println("Paso 4: Producto eliminado lógicamente)");
//
//    }
    
}
