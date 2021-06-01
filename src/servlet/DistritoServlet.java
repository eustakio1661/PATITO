package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.DistritoDTO;
import dao.DAOFactory;
import interfaces.DistritoDAO;

/**
 * Servlet implementation class DistritoServlet
 */
@WebServlet(name = "ds", urlPatterns = { "/ds" })
public class DistritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Entró al Servlet de Cliente");
		String opcion = request.getParameter("opcion") != null ? request.getParameter("opcion") : "error";

		try {
			switch (opcion) {

			case "listado":
				listar(request, response);
				break;

			default:
				System.out.println("Error en la opcion");
				break;
			}
		} catch (Exception e) {
			// response.sendRedirect("error.jsp");
			System.out.println("Error inesperado en el Distrito Servlet");
		}

	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ingreso al proceso ListarDistrito");

        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        DistritoDAO dao = fabrica.getDistritoDAO();
        ArrayList<DistritoDTO> lista = dao.listarDistrito();

        request.setAttribute("lstDistritos", lista);
        request.getRequestDispatcher("").forward(request, response);

    }
}
