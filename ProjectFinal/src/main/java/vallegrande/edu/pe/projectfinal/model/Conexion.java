package vallegrande.edu.pe.projectfinal.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3307/sistema_productos";

    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    public static Connection getConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "Driver JDBC de MySQL no encontrado",
                    e
            );
        }
    }
}