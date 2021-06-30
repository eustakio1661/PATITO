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

import beans.EmpleadoDTO;
import dao.DAOFactory;

@MultipartConfig
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
        case "perfil":
            actualizarPerfil(request, response);
            break;
        case "actualizarEstado":
            actualizarEstado(request, response);
            break;
        default:
            System.out.println("----------------------------");
            System.out.println("Cerrando Session");
            System.out.println("----------------------------");
            request.getSession().invalidate();
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void actualizarEstado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codigo = Integer.parseInt(request.getParameter("id"));

        EmpleadoDTO e = new EmpleadoDTO();

        e.setId(codigo);

        DAOFactory f = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        int ok = f.getEmpleadoDAO().actualizarEstado(e);

        Map<String, Object> data = new LinkedHashMap<String, Object>();

        if (ok != 0) {
            data.put("ok", true);
            data.put("titulo", "Actualizado");
            data.put("mensaje", "Se actualizo estado del empleado correctamente");
            data.put("tipo", "success");
        } else {
            data.put("ok", false);
            data.put("titulo", "Error");
            data.put("mensaje", "No se pudo actualizar estado del empleado");
            data.put("tipo", "error");
        }
        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void actualizarPerfil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String imagen = request.getParameter("imgEmpleado");
        String nombre = request.getParameter("txtNombreEmpleado");
        String apellido = request.getParameter("txtApellidoEmpleado");
        String telefono = request.getParameter("txtTelefonoEmpleado");
        String direccion = request.getParameter("txtDireccionEmpleado");
        String correo = request.getParameter("txtEmailEmpleado");
        String clave = request.getParameter("txtClaveEmpleado");
        int codigo = Integer.parseInt(request.getParameter("txtIdEmpleado"));
        
        if (imagen == null) {
            imagen = "https://res.cloudinary.com/dfuuywyk9/image/upload/v1621437436/l60Hf_megote.png";
        }

        EmpleadoDTO em = new EmpleadoDTO();
        em.setImagen(imagen);
        em.setNombre(nombre);
        em.setApellido(apellido);
        em.setTelefono(telefono);
        em.setDireccion(direccion);
        em.setCorreo(correo);
        em.setClave(clave);
        em.setId(codigo);

        DAOFactory f = DAOFactory.getDAOFactory(DAOFactory.MYSQL);

        int ok = f.getEmpleadoDAO().actualizarPerfilEmpleado(em);
        EmpleadoDTO e = f.getEmpleadoDAO().buscarEmpleado(codigo);
        Map<String, Object> data = new LinkedHashMap<String, Object>();

        if (ok == 0) {
            data.put("ok", false);
            data.put("titulo", "Error");
            data.put("mensaje", "No se pudo actualizar perfil");
            data.put("tipo", "error");
            System.out.println("No se pudo actualizar perfil");
        } else {
            data.put("ok", true);
            data.put("titulo", "Perfil Actualizado");
            data.put("mensaje", "Se ha actualizado su perfil correctamente");
            data.put("tipo", "success");
            System.out.println("Se ha actualizado su perfil correctamente");
        }

        request.getSession().setAttribute("e", e);

        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

    private void buscarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

        String url = "";
        String mensajeAcceso = "";
        
        if (e != null) {
            System.out.println("Usted ingresó con éxito...!");
            url = "home.jsp";
            mensajeAcceso = "Bienvenido(a) " + e.getNombre() + " " + e.getApellido();
        } else {
            System.out.println("Hubo un error al ingresar...");
            mensajeAcceso = "Usuario/Correo o Contraseña no validos";
            url = "login.jsp";
        }
        request.getSession().setAttribute("e", e);
        request.setAttribute("mensajeAcceso", mensajeAcceso);
        request.getRequestDispatcher(url).forward(request, response);

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

        Map<String, Object> data = new LinkedHashMap<String, Object>();

        if (ok != 0) {
            data.put("ok", true);
            data.put("titulo", "Registrado");
            data.put("mensaje", "Se ha registrado al empleado(a) " + nombre + " " + apellido + " correctamente");
            data.put("tipo", "success");
        } else {
            data.put("ok", false);
            data.put("titulo", "Error");
            data.put("mensaje", "No se pudo registrar al empleado(a)");
            data.put("tipo", "error");
        }

        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int codigo = Integer.parseInt(request.getParameter("txtIdEmpleado"));
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

        Map<String, Object> data = new LinkedHashMap<String, Object>();

        if (ok == 0) {
            data.put("ok", false);
            data.put("titulo", "Error");
            data.put("mensaje", "No se pudo actualizar al empleado");
            data.put("tipo", "error");
        } else {
            data.put("ok", true);
            data.put("titulo", "Actualizado");
            data.put("mensaje", "Se ha actualizado el empleado " + nombre + " " + apellido + " correctamente");
            data.put("tipo", "success");
        }
        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int codigo = Integer.parseInt(request.getParameter("id"));

        EmpleadoDTO e = new EmpleadoDTO();

        e.setId(codigo);

        DAOFactory f = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        int ok = f.getEmpleadoDAO().eliminar(e);

        Map<String, Object> data = new LinkedHashMap<String, Object>();

        if (ok != 0) {
            data.put("ok", true);
            data.put("titulo", "Eliminado");
            data.put("mensaje", "Se elimino al empleado correctamente");
            data.put("tipo", "success");
        } else {
            data.put("ok", false);
            data.put("titulo", "Error");
            data.put("mensaje", "No se pudo eliminar al empleado");
            data.put("tipo", "error");
        }

        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

    private void listarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {       
        int estado;
        if(request.getParameter("cboEstado") == null) {
            estado = 1;
        }else {
            estado = Integer.parseInt(request.getParameter("cboEstado"));
        }  
        DAOFactory f = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ArrayList<EmpleadoDTO> listado = f.getEmpleadoDAO().listadoEmpleadoEstado(estado);

        request.setAttribute("listarEmpleado", listado);
        request.getRequestDispatcher("listado-empleados.jsp").forward(request, response);

    }

}
