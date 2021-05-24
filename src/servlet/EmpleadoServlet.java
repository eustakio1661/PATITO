package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.EmpleadoDTO;
import dao.DAOFactory;

@WebServlet(name = "emse", urlPatterns = { "/emse" })
public class EmpleadoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opcion = request.getParameter("btn");
        switch (opcion) {
        case "registro":
            registrarEmpleado(request, response);
            break;
        case "actualizar":
            actualizarEmpleado(request, response);
            break;
        case "eliminar":
            eliminarEmpleado(request, response);
            break;
        case "listar":
            listarEmpleado(request, response);
            break;
        default:
            break;
        }
    }

    private void registrarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("#");
        String nombre = request.getParameter("#");
        String apellido = request.getParameter("#");
        String telefono = request.getParameter("#");
        String direccion = request.getParameter("#");
        String correo = request.getParameter("#");
        String usuario = request.getParameter("#");
        String clave = request.getParameter("#");
        int idTipo = Integer.parseInt(request.getParameter("#"));
        int estado = Integer.parseInt(request.getParameter("#"));
        String mensaje = "";
        String url = "";

        EmpleadoDTO e = new EmpleadoDTO();
        e.setDni(dni);
        e.setNombre(nombre);
        e.setApellido(apellido);
        e.setTelefono(telefono);
        e.setDireccion(direccion);
        e.setCorreo(correo);
        e.setUsuario(usuario);
        e.setClave(clave);
        e.setIdTipo(idTipo);
        e.setEstado(estado);

        DAOFactory f = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

        int ok = f.getEmpleadoDAO().registrar(e);

        if (ok != 0) {
            mensaje = "#";
            url = "#";
        } else {
            mensaje = "#";
            url = "#";
        }
        request.setAttribute("registroEmpleado", mensaje);
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("#"));
        String dni = request.getParameter("#");
        String nombre = request.getParameter("#");
        String apellido = request.getParameter("#");
        String telefono = request.getParameter("#");
        String direccion = request.getParameter("#");
        String correo = request.getParameter("#");
        String usuario = request.getParameter("#");
        String clave = request.getParameter("#");
        int idTipo = Integer.parseInt(request.getParameter("#"));
        int estado = Integer.parseInt(request.getParameter("#"));
        String imagen = request.getParameter("#");
        String mensaje = "";
        String url = "";

        EmpleadoDTO e = new EmpleadoDTO();
        e.setId(id);
        e.setDni(dni);
        e.setNombre(nombre);
        e.setApellido(apellido);
        e.setTelefono(telefono);
        e.setDireccion(direccion);
        e.setCorreo(correo);
        e.setUsuario(usuario);
        e.setClave(clave);
        e.setIdTipo(idTipo);
        e.setEstado(estado);
        e.setImagen(imagen);

        DAOFactory f = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

        int ok = f.getEmpleadoDAO().actualizar(e);

        if (ok != 0) {
            mensaje = "#";
            url = "#";
        } else {
            mensaje = "#";
            url = "#";
        }
        request.setAttribute("actualizarEmpleado", mensaje);
        request.getRequestDispatcher(url).forward(request, response);

    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("#"));
        String dni = request.getParameter("#");
        String nombre = request.getParameter("#");
        String apellido = request.getParameter("#");
        String telefono = request.getParameter("#");
        String direccion = request.getParameter("#");
        String correo = request.getParameter("#");
        String usuario = request.getParameter("#");
        String clave = request.getParameter("#");
        int idTipo = Integer.parseInt(request.getParameter("#"));
        int estado = Integer.parseInt(request.getParameter("#"));
        String imagen = request.getParameter("#");
        String mensaje = "";
        String url = "";

        EmpleadoDTO e = new EmpleadoDTO();
        e.setId(id);
        e.setDni(dni);
        e.setNombre(nombre);
        e.setApellido(apellido);
        e.setTelefono(telefono);
        e.setDireccion(direccion);
        e.setCorreo(correo);
        e.setUsuario(usuario);
        e.setClave(clave);
        e.setIdTipo(idTipo);
        e.setEstado(estado);
        e.setImagen(imagen);

        DAOFactory f = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

        int ok = f.getEmpleadoDAO().eliminar(e);

        if (ok != 0) {
            mensaje = "#";
            url = "#";
        } else {
            mensaje = "#";
            url = "#";
        }
        request.setAttribute("eliminarEmpleado", mensaje);
        request.getRequestDispatcher(url).forward(request, response);

    }

    private void listarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAOFactory f = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ArrayList<EmpleadoDTO> listado = f.getEmpleadoDAO().listado();
        
        request.setAttribute("listarEmpleado", listado);
        request.getRequestDispatcher("#").forward(request, response);

    }

}
