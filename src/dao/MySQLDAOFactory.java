package dao;


import interfaces.EmpleadoDAO;
import interfaces.ProductoDAO;
import mantenimiento.MySQLEmpleadoDAO;
import mantenimiento.MySQLProductoDAO;

public class MySQLDAOFactory extends DAOFactory {

    public EmpleadoDAO getEmpleadoDAO() {
        // Llama a la clase implementación o Gestión
        return new MySQLEmpleadoDAO();
    }

    @Override
    public ProductoDAO getProductoDAO() {
        return new MySQLProductoDAO();
    }
}
