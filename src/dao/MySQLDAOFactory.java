package dao;


import interfaces.EmpleadoDAO;
import mantenimiento.MySQLEmpleadoDAO;

public class MySQLDAOFactory extends DAOFactory {

    public EmpleadoDAO getEmpleadoDAO() {
        // Llama a la clase implementaci�n o Gesti�n
        return new MySQLEmpleadoDAO();
    }
}
