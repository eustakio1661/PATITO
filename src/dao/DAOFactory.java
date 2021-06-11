package dao;

import interfaces.ClienteDAO;
import interfaces.DistritoDAO;
import interfaces.EmpleadoDAO;
import interfaces.ProductoDAO;
import interfaces.VentaDAO;
import interfaces.ReporteDAO;

public abstract class DAOFactory {

    public static final int MYSQL = 1;
    public static final int SQL = 2;
    public static final int ORACLE = 3;
    
    public abstract EmpleadoDAO getEmpleadoDAO();  
    public abstract ProductoDAO getProductoDAO();
    public abstract ClienteDAO getClienteDAO();
    public abstract DistritoDAO getDistritoDAO();
    public abstract VentaDAO getVentaDao();
    public abstract ReporteDAO getReporteDAO();
    
    public static DAOFactory getDAOFactory(int qBD) {
        switch (qBD) {
        case MYSQL:
            return new MySQLDAOFactory();          
        default:
            
            return null;
        }
    }
    
}
