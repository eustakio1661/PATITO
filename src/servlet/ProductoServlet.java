package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ProductoDTO;
import dao.DAOFactory;
import interfaces.ProductoDAO;


/**
 * Servlet implementation class ProductoServlet
 */
@WebServlet(name = "ps", urlPatterns = { "/ps" })
public class ProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Entró al Servlet de Producto");
		String opcion = request.getParameter("opcion") != null ? request.getParameter("opcion") : "error";

		try {
			switch (opcion) {
			case "registrar":
				registrar(request, response);
				break;
			case "actualizar":
				actualizar(request, response);
				break;
			case "eliminar":
				eliminar(request, response);
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
			System.out.println("Error inesperado en el Producto Servlet");
		}
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ingreso al proceso BuscarProducto");

        int codigo = Integer.parseInt(request.getParameter(""));

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ProductoDTO p = factory.getProductoDAO().buscar(codigo);
        request.setAttribute("p", p);

        request.getRequestDispatcher("").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ingreso al proceso ListarProducto");

		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ProductoDAO dao = factory.getProductoDAO();
		ArrayList<ProductoDTO> lista = dao.listado();

		request.setAttribute("lstProductos", lista);
		request.getRequestDispatcher("").forward(request, response);

	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Ingreso al proceso ActualizarProducto");

		int codigo = Integer.parseInt(request.getParameter(""));

		ProductoDTO p = new ProductoDTO();
		p.setIdProducto(codigo);
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ProductoDAO dao = fabrica.getProductoDAO();

		int ok = dao.eliminar(p);
		if (ok != 0) {

		} else {

		}

	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Ingreso al proceso ActualizarProducto");

		int codigo = Integer.parseInt(request.getParameter(""));
		String descripcion = request.getParameter("");
		double precio = Double.parseDouble(request.getParameter(""));
		int cantidad = Integer.parseInt(request.getParameter(""));
		int idcategoria = Integer.parseInt(request.getParameter(""));
		String imagen = request.getParameter("");

		ProductoDTO p = new ProductoDTO();
		p.setIdProducto(codigo);
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		p.setCantidad(cantidad);
		p.setIdCategoria(idcategoria);
		p.setImagen(imagen);
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ProductoDAO dao = fabrica.getProductoDAO();

		int ok = dao.actualizar(p);
		if (ok != 0) {

		} else {

		}

	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Ingreso al proceso RegistrarProducto");

		String descripcion = request.getParameter("");
		double precio = Double.parseDouble(request.getParameter(""));
		int cantidad = Integer.parseInt(request.getParameter(""));
		int idcategoria = Integer.parseInt(request.getParameter(""));
		String imagen = request.getParameter("");

		ProductoDTO p = new ProductoDTO();
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		p.setCantidad(cantidad);
		p.setIdCategoria(idcategoria);
		p.setImagen(imagen);
		DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ProductoDAO dao = fabrica.getProductoDAO();

		int ok = dao.registrar(p);
		if (ok != 0) {

		} else {

		}

	}

}
