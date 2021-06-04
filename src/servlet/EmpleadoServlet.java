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

        String opcion = request.getParameter("opcion");
        opcion = (opcion == null) ? "cerrar" : opcion;
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
        case "login":
            loginEmpleado(request, response);
            break;
        case "buscar":
            buscarEmpleado(request, response);
            break;
        default:
            request.getSession().invalidate();
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void buscarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("idEmp"));

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        EmpleadoDTO e = factory.getEmpleadoDAO().buscarEmpleado(codigo);
        
        request.setAttribute("empleadoEncontrado", e);
        request.getRequestDispatcher("crud-empleado.jsp").forward(request, response);
        
    }

    private void loginEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("txtCorreo");
        String clave = request.getParameter("txtClave");

        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        EmpleadoDTO e = fabrica.getEmpleadoDAO().validarAcceso(usuario, clave);

        if (e != null) {
            System.out.println("Usted ingresó con éxito...!");
        } else {
            System.out.println("Hubo un error al ingresar...");
        }
        request.getSession().setAttribute("e", e);
        request.getRequestDispatcher("home.jsp").forward(request, response);

    }

    private void registrarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("txtDNIEmpleado");
        String nombre = request.getParameter("txtNombreEmpleado");
        String apellido = request.getParameter("txtApellidoEmpleado");
        String telefono = request.getParameter("txtTelefonoEmpleado");
        String direccion = request.getParameter("txtDireccionEmpleado");
        int idTipo = Integer.parseInt(request.getParameter("cboTipoEmpleado"));

        EmpleadoDTO e = new EmpleadoDTO();
        e.setDni(dni);
        e.setNombre(nombre);
        e.setApellido(apellido);
        e.setTelefono(telefono);
        e.setDireccion(direccion);
        e.setIdTipo(idTipo);

        DAOFactory f = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

        int ok = f.getEmpleadoDAO().registrar(e);

        if (ok == 0) {
            System.out.println("Error al registrar empleado");
        } else {
            System.out.println("Empleado registrado con éxito");
        }
        request.getRequestDispatcher("crud-empleado.jsp").forward(request, response);
    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entró a actualizar");
        
        int codigo = Integer.parseInt(request.getParameter("codeEmpleado"));
        String dni = request.getParameter("txtDNIEmpleado");
        String nombre = request.getParameter("txtNombreEmpleado");
        String apellido = request.getParameter("txtApellidoEmpleado");
        String telefono = request.getParameter("txtTelefonoEmpleado");
        String direccion = request.getParameter("txtDireccionEmpleado");
        int idTipo = Integer.parseInt(request.getParameter("cboTipoEmpleado"));

        EmpleadoDTO e = new EmpleadoDTO();
        e.setId(codigo);
        e.setDni(dni);
        e.setNombre(nombre);
        e.setApellido(apellido);
        e.setTelefono(telefono);
        e.setDireccion(direccion);
        e.setIdTipo(idTipo);


        DAOFactory f = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

        int ok = f.getEmpleadoDAO().actualizarEmpleado(e);

        if (ok == 0) {
            System.out.println("Error al actualizar empleado...");
        } else {
            System.out.println("Empleado actualizado con éxito...!");
        }
        request.getRequestDispatcher("listado-empleados.jsp").forward(request, response);

    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       int codigo = Integer.parseInt(request.getParameter("idEmpleado"));
       
       EmpleadoDTO e = new EmpleadoDTO();
       
       e.setId(codigo);       
        
        DAOFactory f = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        int ok = f.getEmpleadoDAO().eliminar(e);

        if (ok == 0) {
            System.out.println("Error al eliminar empleado");
        } else {
            System.out.println("Empleado eliminado con éxito!");
        }
        request.getRequestDispatcher("listado-empleados.jsp").forward(request, response);
        
    }

    private void listarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOFactory f = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ArrayList<EmpleadoDTO> listado = f.getEmpleadoDAO().listado();

        request.setAttribute("listarEmpleado", listado);
        request.getRequestDispatcher("listado-empleados.jsp").forward(request, response);

    }

}
