package dao;


import interfaces.EmpleadoDAO;
import interfaces.ProductoDAO;
import mantenimiento.MySQLEmpleadoDAO;
import mantenimiento.MySQLProductoDAO;

public class MySQLDAOFactory extends DAOFactory {

    public EmpleadoDAO getEmpleadoDAO() {
        // Llama a la clase implementaci�n o Gesti�n
        return new MySQLEmpleadoDAO();
    }

    @Override
    public ProductoDAO getProductoDAO() {
        return new MySQLProductoDAO();
    }
}
