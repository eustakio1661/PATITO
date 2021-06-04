package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ClienteDTO;
import beans.Detalle_PedidoDTO;
import beans.ProductoDTO;
import dao.DAOFactory;
import interfaces.ClienteDAO;
import interfaces.ProductoDAO;

/**
 * Servlet implementation class VentaServlet
 */
@WebServlet(name = "venser", urlPatterns = { "/venser" })
public class VentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("Entró al Servlet de Producto");
        String opcion = request.getParameter("opcion") != null ? request.getParameter("opcion") : "error";

        try {
            switch (opcion) {
            case "cargCliente":
                cargarCliente(request, response);
                break;
            case "cargProducto":
                cargarProducto(request, response);
                break;
            case "listados":
                listadoClienteyProducto(request, response);
                break;
            case "agrCompra":
                agregarCompra(request, response);
                break;
            case "eliCompra":
                eliminarCompraCarro(request, response);
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

    private void eliminarCompraCarro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Detalle_PedidoDTO> carro = (ArrayList<Detalle_PedidoDTO>) request.getSession().getAttribute("carro");
        int cantidadProductos = (int) request.getSession().getAttribute("cantidadProductos");
        double subTotalVentas = (double) request.getSession().getAttribute("subTotalVentas");
       
        int idprod = Integer.parseInt(request.getParameter("codigo"));
        for (Detalle_PedidoDTO d : carro) {
            if (d.getId_pro() == idprod) {
                cantidadProductos -= d.getCantidad();
                subTotalVentas -= d.getImporte();
                carro.remove(d);
                break;
            }
        }
        
        request.getSession().setAttribute("carro", carro);
        request.getSession().setAttribute("cantidadProductos", cantidadProductos);
        request.getSession().setAttribute("subTotalVentas", subTotalVentas);
        
        listadoClienteyProducto(request, response);
        
    }

    private void cargarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ProductoDTO p = factory.getProductoDAO().buscar(codigo);
        request.getSession().setAttribute("productoEncontrado", p);

        listadoClienteyProducto(request, response);
        
    }
 
    private void agregarCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        int cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
        ProductoDTO p = (ProductoDTO) request.getSession().getAttribute("productoEncontrado");
        //Traer atributos de session
        ArrayList<Detalle_PedidoDTO> carro = (ArrayList<Detalle_PedidoDTO>) request.getSession().getAttribute("carro");
        int cantidadProductos = (int) request.getSession().getAttribute("cantidadProductos");
        double subTotalVentas = (double) request.getSession().getAttribute("subTotalVentas");
        
        //Agregamos al carro y actualizamos los acumuladores
        Detalle_PedidoDTO detalle = new Detalle_PedidoDTO();
        detalle.setId_pro(p.getIdProducto());
        detalle.setCantidad(cantidad);
        detalle.setPrecio(p.getPrecio());
        
        //Adicionales
        detalle.setNombreProd(p.getDescripcion());
        detalle.setImporte(cantidad * p.getPrecio());
       
        cantidadProductos += cantidad;
        subTotalVentas += detalle.getImporte();   
        
        carro.add(detalle);
    
        //Envia las variables globales a nivel de session
        request.getSession().setAttribute("carro", carro);
        request.getSession().setAttribute("cantidadProductos", cantidadProductos);
        request.getSession().setAttribute("subTotalVentas", subTotalVentas);
        //Muestra la pagina
        listadoClienteyProducto(request, response);
        
    }

    private void listadoClienteyProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ingreso al proceso ListarProducto");

        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ClienteDAO dao = fabrica.getClienteDAO();
        ProductoDAO dao1 = fabrica.getProductoDAO();
        ArrayList<ClienteDTO> lista = dao.listarClientexDistrito();
        ArrayList<ProductoDTO> lista1 = dao1.listado();

        request.setAttribute("lstProductos", lista1);
        request.setAttribute("lstClientes", lista);
        request.getRequestDispatcher("/catalogo.jsp").forward(request, response);
        
    }

    private void cargarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ingreso al proceso SeleccionarCliente");
        int codigo = Integer.parseInt(request.getParameter("codigo"));

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ClienteDTO c = factory.getClienteDAO().buscarCliente(codigo);
        
        request.getSession().setAttribute("clienteEncontrado", c);
        
        listadoClienteyProducto(request, response);
    }

}
