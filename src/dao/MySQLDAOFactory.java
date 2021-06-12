package dao;


import interfaces.EmpleadoDAO;
import interfaces.ProductoDAO;
import interfaces.VentaDAO;
import interfaces.ReporteDAO;
import interfaces.ClienteDAO;
import interfaces.DistritoDAO;
import mantenimiento.MySQLEmpleadoDAO;
import mantenimiento.MySQLProductoDAO;
import mantenimiento.MySQLVentaDAO;
import mantenimiento.MySQLClienteDAO;
import mantenimiento.MySQLDistritoDAO;
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
    	return new MySQLDistritoDAO();
    }

    @Override
    public VentaDAO getVentaDao() {
        // TODO Auto-generated method stub
        return new MySQLVentaDAO();
    }

    public ReporteDAO getReporteDAO() {
        return new MySQLReporteDAO();
    }
}
