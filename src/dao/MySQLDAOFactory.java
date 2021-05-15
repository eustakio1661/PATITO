package dao;


import interfaces.EmpleadoDAO;
import mantenimiento.MySQLEmpleadoDAO;

public class MySQLDAOFactory extends DAOFactory {

    public EmpleadoDAO getEmpleadoDAO() {
        // Llama a la clase implementación o Gestión
        return new MySQLEmpleadoDAO();
    }
}
