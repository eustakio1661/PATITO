package dao;


import interfaces.EmpleadoDAO;
import interfaces.ProductoDAO;
import interfaces.ReporteDAO;
import interfaces.ClienteDAO;
import interfaces.DistritoDAO;
import mantenimiento.MySQLEmpleadoDAO;
import mantenimiento.MySQLProductoDAO;
import mantenimiento.MySQLClienteDAO;
import mantenimiento.MySQLDistrito;
import mantenimiento.MySQLReporteDAO;

public class MySQLDAOFactory extends DAOFactory {

    public EmpleadoDAO getEmpleadoDAO() {
        // Llama a la clase implementación o Gestión
        return new MySQLEmpleadoDAO();
    }

    @Override
    public ProductoDAO getProductoDAO() {
        return new MySQLProductoDAO();
    }
    
    public ClienteDAO getClienteDAO() {
    	return new MySQLClienteDAO();
    }
    
    public DistritoDAO getDistritoDAO() {
    	return new MySQLDistrito();
    }

    public ReporteDAO getReporteDAO() {
        return new MySQLReporteDAO();
    }
}
