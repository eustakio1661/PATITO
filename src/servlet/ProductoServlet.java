package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.ProductoDTO;
import dao.DAOFactory;
import interfaces.ProductoDAO;

@MultipartConfig
@WebServlet(name = "ps", urlPatterns = { "/ps" })
public class ProductoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
            case "catalogo":
                listarCatalogo(request, response);
                break;
            default:
                System.out.println("Error en la opcion");
                break;
            }
        } catch (Exception e) {
            System.out.println("Error inesperado en el Producto Servlet");
        }
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ingreso al proceso BuscarProducto");

        int codigo = Integer.parseInt(request.getParameter("codigo"));

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ProductoDTO p = factory.getProductoDAO().buscar(codigo);
        request.setAttribute("productoEncontrado", p);

        request.getRequestDispatcher("crud-producto.jsp").forward(request, response);
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ingreso al proceso ListarProducto");

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ProductoDAO dao = factory.getProductoDAO();
        ArrayList<ProductoDTO> lista = dao.listado();

        request.setAttribute("lstProductos", lista);
        request.getRequestDispatcher("listado-productos.jsp").forward(request, response);

    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("Ingreso al proceso EliminarProducto");

        int codigo = Integer.parseInt(request.getParameter("codigo"));

        ProductoDTO p = new ProductoDTO();
        p.setIdProducto(codigo);
        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ProductoDAO dao = fabrica.getProductoDAO();

        int ok = dao.eliminar(p);

        Map<String, Object> data = new LinkedHashMap<String, Object>();

        if (ok != 0) {
            data.put("ok", true);
            data.put("titulo", "Eliminado");
            data.put("mensaje", "Se elimino el producto correctamente");
            data.put("tipo", "success");
        } else {
            data.put("ok", false);
            data.put("titulo", "Error");
            data.put("mensaje", "No se pudo eliminar el producto");
            data.put("tipo", "error");
        }

        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Ingreso al proceso ActualizarProducto");

        int codigo = Integer.parseInt(request.getParameter("txtCodigoProd"));
        String descripcion = request.getParameter("txtDescripcionProd");
        double precio = Double.parseDouble(request.getParameter("txtPrecioProd"));
        int cantidad = Integer.parseInt(request.getParameter("txtStockProd"));
        int idcategoria = Integer.parseInt(request.getParameter("cboCategoriaProd"));
        String imagen = request.getParameter("imgProducto");

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

        Map<String, Object> data = new LinkedHashMap<String, Object>();

        if (ok != 0) {
            data.put("ok", true);
            data.put("titulo", "Registrado");
            data.put("mensaje", "Se ah actualizado el producto " + descripcion + " correctamente");
            data.put("tipo", "success");
        } else {
            data.put("ok", false);
            data.put("titulo", "Error");
            data.put("mensaje", "No se pudo actualizar el producto");
            data.put("tipo", "error");
        }

        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Ingreso al proceso RegistrarProducto");

        String descripcion = request.getParameter("txtDescripcionProd");
        double precio = Double.parseDouble(request.getParameter("txtPrecioProd"));
        int cantidad = Integer.parseInt(request.getParameter("txtStockProd"));
        int idcategoria = Integer.parseInt(request.getParameter("cboCategoriaProd"));
        String imagen = request.getParameter("imgProducto");

        ProductoDTO p = new ProductoDTO();
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setCantidad(cantidad);
        p.setIdCategoria(idcategoria);
        p.setImagen(imagen);
        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ProductoDAO dao = fabrica.getProductoDAO();

        int ok = dao.registrar(p);

        Map<String, Object> data = new LinkedHashMap<String, Object>();

        if (ok != 0) {
            data.put("ok", true);
            data.put("titulo", "Registrado");
            data.put("mensaje", "Se ah registrado el producto " + descripcion + " correctamente");
            data.put("tipo", "success");
        } else {
            data.put("ok", false);
            data.put("titulo", "Error");
            data.put("mensaje", "No se pudo registrar el producto");
            data.put("tipo", "error");
        }

        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

    private void listarCatalogo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Ingreso al proceso ListarCatalogo");

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ProductoDAO dao = factory.getProductoDAO();
        ArrayList<ProductoDTO> lista = dao.listado();

        request.setAttribute("lstProductos", lista);
        request.getRequestDispatcher("catalogo.jsp").forward(request, response);

    }

}
