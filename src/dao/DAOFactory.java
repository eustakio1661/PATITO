package dao;

import interfaces.EmpleadoDAO;
import interfaces.ProductoDAO;

public abstract class DAOFactory {

    public static final int MYSQL = 1;
    public static final int SQL = 2;
    public static final int ORACLE = 3;
    
    public abstract EmpleadoDAO getEmpleadoDAO();  
    public abstract ProductoDAO getProductoDAO();
    
    public static DAOFactory getDAOFactory(int qBD) {
        switch (qBD) {
        case MYSQL:
            return new MySQLDAOFactory();          
        default:
            
            return null;
        }
    }
    
}
