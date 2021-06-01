package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ClienteDTO;
import dao.DAOFactory;
import interfaces.ClienteDAO;


/**
 * Servlet implementation class ClienteServlet
 */
@WebServlet(name = "cs", urlPatterns = { "/cs" })
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("Entró al Servlet de Cliente");
        String opcion = request.getParameter("opcion") != null ? request.getParameter("opcion") : "error";

        try {
            switch (opcion) {
            case "registrar":
                registrar(request, response);
                break;
            case "actualizar":
                actualizar(request, response);
                break;
            
            case "listado":
                listar(request, response);
                break;
            case "buscar":
                buscar(request, response);
                break;
            default:
                System.out.println("Error en la opcion");
                break;
            }
        } catch (Exception e) {
            // response.sendRedirect("error.jsp");
            System.out.println("Error inesperado en el Cliente Servlet");
        }
	
	}
	
	private void registrar(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Ingreso al proceso RegistrarCliente");
		
		int codigo = Integer.parseInt(request.getParameter(""));
        int codigoDistrito = Integer.parseInt(request.getParameter(""));
        String dni = request.getParameter("");
		String nombre = request.getParameter("");
		String apellido = request.getParameter("");
		String direccion = request.getParameter("");
		String telefono = request.getParameter("");
		int estado = Integer.parseInt(request.getParameter(""));
		

        ClienteDTO c = new ClienteDTO();
        c.setCodigo(codigo);
        c.setCodigoDistrito(codigoDistrito);
        c.setDni(dni);
        c.setNombre(nombre);
        c.setApellido(apellido);
		c.setDireccion(direccion);
		c.setTelefono(telefono);
		c.setEstado(estado);
        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ClienteDAO dao = fabrica.getClienteDAO();

        int ok = dao.registrarCliente(c);
        if (ok != 0) {

        } else {

        }
	}
	
	private void actualizar(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Ingreso al proceso Actualizar Cliente");
		
		int codigo = Integer.parseInt(request.getParameter(""));
        int codigoDistrito = Integer.parseInt(request.getParameter(""));
        String dni = request.getParameter("");
		String nombre = request.getParameter("");
		String apellido = request.getParameter("");
		String direccion = request.getParameter("");
		String telefono = request.getParameter("");
		int estado = Integer.parseInt(request.getParameter(""));

        ClienteDTO c = new ClienteDTO();
        c.setCodigo(codigo);
        c.setCodigoDistrito(codigoDistrito);
        c.setDni(dni);
        c.setNombre(nombre);
        c.setApellido(apellido);
		c.setDireccion(direccion);
		c.setTelefono(telefono);
		c.setEstado(estado);
        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ClienteDAO dao = fabrica.getClienteDAO();

        int ok = dao.actualizarCliente(c);
        if (ok != 0) {

        } else {

        }

    }
	
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ingreso al proceso ListarProducto");

        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ClienteDAO dao = fabrica.getClienteDAO();
        ArrayList<ClienteDTO> lista = dao.listarCliente();

        request.setAttribute("lstClientes", lista);
        request.getRequestDispatcher("").forward(request, response);

    }
	
	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ingreso al proceso BuscarCliente");

        int codigo = Integer.parseInt(request.getParameter(""));

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ClienteDTO c = factory.getClienteDAO().buscarCliente(codigo);
        request.setAttribute("c", c);

        request.getRequestDispatcher("").forward(request, response);
    }
}
