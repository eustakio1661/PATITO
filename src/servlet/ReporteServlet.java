package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ListadoEntreFechasDTO;
import beans.ReporteClienteDTO;
import dao.DAOFactory;
import interfaces.ReporteDAO;

/**
 * Servlet implementation class ReporteServlet
 */
@WebServlet(name = "rese", urlPatterns = { "/rese" })
public class ReporteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String opcion = request.getParameter("opcion");
        opcion = (opcion == null) ? "cerrar" : opcion;
        switch (opcion) {
        case "reporte":
            reporteEntreFechas(request, response);
        case "reporteCliente":
            reporteCliente(request, response);
            break;
        case "listado":
            listadoVentas(request, response);
            break;
        default:
            System.out.println("Error en la opción");
        }
    }

    private void reporteCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ingreso al proceso Reporte Cliente");

        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ReporteDAO dao = fabrica.getReporteDAO();
        ArrayList<ReporteClienteDTO> lista = dao.reporteCliente();

        request.setAttribute("lstReporteClientes", lista);
        request.getRequestDispatcher("reporte-clientes.jsp").forward(request, response);
    }

    private void listadoVentas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOFactory f = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ArrayList<ListadoEntreFechasDTO> listado = f.getReporteDAO().lista();

        request.setAttribute("listadoVentas", listado);
        request.getRequestDispatcher("reporte-ventas.jsp").forward(request, response);

    }

    private void reporteEntreFechas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fecha1 = request.getParameter("txtFechaInicio");
        String fecha2 = request.getParameter("txtFechaFinal");

        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ArrayList<ListadoEntreFechasDTO> lista = fabrica.getReporteDAO().reporte(fecha1, fecha2);

        request.setAttribute("listadoVentas", lista);
        request.getRequestDispatcher("reporte-ventas.jsp").forward(request, response);

    }

}
