package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion8 {

    public static Connection getConexion() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String URL = "jdbc:mysql://localhost/idk?useSSL=false&useTimezone=true&serverTimezone=UTC";
            String USER = "root";
            String PASSWORD = "mysql";
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException ex) {
            System.out.println("Error >> Driver no Instalado CNFE : " + ex);
        } catch (SQLException ex) {
            System.out.println("Error >> Driver no Instalado SQLE : " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error >> general : " + ex.getMessage());
        }

        return connection;

    }

    public static void closeConexion(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Problemas al cerrar la conexion");
        }
    }

}
