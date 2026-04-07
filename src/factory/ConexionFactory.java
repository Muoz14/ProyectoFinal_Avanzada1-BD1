package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionFactory {
    
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto_final";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection getConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos", e);
        }
    }
    
}
