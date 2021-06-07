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

import beans.ClienteDTO;
import dao.DAOFactory;
import interfaces.ClienteDAO;

@MultipartConfig
@WebServlet(name = "cs", urlPatterns = { "/cs" })
public class ClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
            System.out.println("Error inesperado en el Cliente Servlet");
            System.out.println(e);
            System.out.println(e.getMessage());
        }

    }


    private void registrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Ingreso al proceso RegistrarCliente");

        int codigoDistrito = Integer.parseInt(request.getParameter("cboDistritoCliente"));
        String dni = request.getParameter("txtDNICliente");
        String nombre = request.getParameter("txtNombreCliente");
        String apellido = request.getParameter("txtApellidoCliente");
        String direccion = request.getParameter("txtDireccionCliente");
        String telefono = request.getParameter("txtTelefonoCliente");

        ClienteDTO c = new ClienteDTO();

        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setTelefono(telefono);
        c.setDni(dni);
        c.setCodigoDistrito(codigoDistrito);
        c.setDireccion(direccion);

        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ClienteDAO dao = fabrica.getClienteDAO();

        int ok = dao.registrarCliente(c);

        Map<String, Object> data = new LinkedHashMap<String, Object>();

        if (ok != 0) {
            data.put("ok", true);
            data.put("titulo", "Registrado");
            data.put("mensaje", "Se ah registrado el cliente " + nombre + " " + apellido + " correctamente");
            data.put("tipo", "success");
        } else {
            data.put("ok", false);
            data.put("titulo", "Error");
            data.put("mensaje", "No se pudo registrar al cliente");
            data.put("tipo", "error");
        }

        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Ingreso al proceso EliminarCliente");

        int codigo = Integer.parseInt(request.getParameter("codigo"));

        ClienteDTO c = new ClienteDTO();
        c.setCodigo(codigo);
        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ClienteDAO dao = fabrica.getClienteDAO();

        int ok = dao.eliminarCliente(c);

        Map<String, Object> data = new LinkedHashMap<String, Object>();

        if (ok != 0) {
            data.put("ok", true);
            data.put("titulo", "Eliminado");
            data.put("mensaje", "Se elimino al cliente correctamente");
            data.put("tipo", "success");
        } else {
            data.put("ok", false);
            data.put("titulo", "Error");
            data.put("mensaje", "No se pudo eliminar al cliente");
            data.put("tipo", "error");
        }

        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Ingreso al proceso Actualizar Cliente");

        int codigo = Integer.parseInt(request.getParameter("txtCodigoCliente"));
        int codigoDistrito = Integer.parseInt(request.getParameter("cboDistritoCliente"));
        String dni = request.getParameter("txtDNICliente");
        String nombre = request.getParameter("txtNombreCliente");
        String apellido = request.getParameter("txtApellidoCliente");
        String direccion = request.getParameter("txtDireccionCliente");
        String telefono = request.getParameter("txtTelefonoCliente");

        ClienteDTO c = new ClienteDTO();

        c.setCodigo(codigo);
        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setTelefono(telefono);
        c.setDni(dni);
        c.setCodigoDistrito(codigoDistrito);
        c.setDireccion(direccion);

        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ClienteDAO dao = fabrica.getClienteDAO();

        int ok = dao.actualizarCliente(c);

        Map<String, Object> data = new LinkedHashMap<String, Object>();

        if (ok != 0) {
            data.put("ok", true);
            data.put("titulo", "Actualizado");
            data.put("mensaje", "Se ah actualizado el cliente " + nombre + " " + apellido + " correctamente");
            data.put("tipo", "success");
        } else {
            data.put("ok", false);
            data.put("titulo", "Error");
            data.put("mensaje", "No se pudo actualizar al cliente");
            data.put("tipo", "error");
        }

        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ingreso al proceso ListarProducto");

        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ClienteDAO dao = fabrica.getClienteDAO();
        ArrayList<ClienteDTO> lista = dao.listarClientexDistrito();

        request.setAttribute("lstClientes", lista);
        request.getRequestDispatcher("listado-clientes.jsp").forward(request, response);

    }

    private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ingreso al proceso BuscarCliente");

        int codigo = Integer.parseInt(request.getParameter("codigo"));

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ClienteDTO c = factory.getClienteDAO().buscarCliente(codigo);
        request.setAttribute("clienteEncontrado", c);

        request.getRequestDispatcher("crud-cliente.jsp").forward(request, response);
    }
}
