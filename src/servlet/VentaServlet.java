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

import beans.BoletaDTO;
import beans.ClienteDTO;
import beans.DetallePedidoDTO;
import beans.EmpleadoDTO;
import beans.PedidoDTO;
import beans.ProductoDTO;
import dao.DAOFactory;
import interfaces.ProductoDAO;
import interfaces.ReporteDAO;
import interfaces.VentaDAO;

@MultipartConfig
@WebServlet(name = "venser", urlPatterns = { "/venser" })
public class VentaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entró al Servlet de Venta");
        String opcion = request.getParameter("opcion") != null ? request.getParameter("opcion") : "error";

        try {
            switch (opcion) {
            case "agrCompra":
                agregarCompra(request, response);
                break;
            case "eliCompra":
                eliminarCompraCarro(request, response);
                break;
            case "canceCliente":
                cancelarCliente(request, response);
                break;
            case "procCompra":
                procesarCompra(request, response);
                break;
            case "buscarCliente":
                buscarCliente(request, response);
                break;
            case "descuento":
                descuentoCliente(request, response);
                break;
            case "canceCompra":
                cancelarCompra(request, response);
                break;
            case "pedidosPendientes":
                pedidosPendientes(request, response);
                break;
            case "actualizarPedido":
                actualizarPedido(request, response);
                break;
            default:
                System.out.println("Error en la opcion");
                break;
            }
        } catch (Exception e) {
            // response.sendRedirect("error.jsp");
            System.out.println("Error inesperado en la Venta Servlet");
            System.out.println(e);
        }
    }


    private void actualizarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ingreso al proceso Actualizar Pedido");
        String url = "";
        int codigo = Integer.parseInt(request.getParameter("codigo"));

        PedidoDTO p = new PedidoDTO();
        p.setId_pe(codigo);
        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        VentaDAO dao = fabrica.getVentaDao();

        int ok = dao.actualizarPedido(p);
        
        if (ok !=0) {
            System.out.println("Se actualizo Pedido");
            url = "pedidos-pendientes.jsp";
        }else {
            System.out.println("Error en actualizar Pedido");
            url = "home.jsp";
        }
        
        request.getRequestDispatcher(url).forward(request, response);
    }


    private void pedidosPendientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ingreso al proceso Pedidos Pendientes");

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ReporteDAO dao = factory.getReporteDAO();
        ArrayList<PedidoDTO> lista = dao.listarPedidosPendientes();

        request.setAttribute("lstPedidosPendientes", lista);
        request.getRequestDispatcher("pedidos-pendientes.jsp").forward(request, response);
    }


    private void cancelarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Cancelar Cliente");
        request.getSession().setAttribute("ClienteCompra", new ClienteDTO());
        request.getSession().setAttribute("ClienteDescuento", new ClienteDTO());
        
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("ok", true);
        
        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void descuentoCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Aplicando Descuento");
        ClienteDTO cliente = (ClienteDTO) request.getSession().getAttribute("ClienteCompra");
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        cliente = factory.getClienteDAO().descuento(cliente.getCodigo());
        request.getSession().setAttribute("ClienteDescuento", cliente);
        request.getRequestDispatcher("compra.jsp").forward(request, response);
    }

    private void cancelarCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().setAttribute("ClienteCompra", new ClienteDTO());
        request.getSession().setAttribute("ClienteDescuento", new ClienteDTO());
        request.getSession().setAttribute("carro", new ArrayList<DetallePedidoDTO>());
        request.getSession().setAttribute("cantidadProductos", 0);
        request.getSession().setAttribute("subTotalVentas", 0.00);

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("ok", true);
        
        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void buscarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dni = request.getParameter("txtDNICli");

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ClienteDTO c = factory.getClienteDAO().buscarClienteDNI(dni);

        Map<String, Object> data = new LinkedHashMap<String, Object>();

        if (c != null) {
            data.put("ok", true);
            data.put("titulo", "Cliente Encontrado");
            data.put("mensaje", "El cliente con DNI " + dni + " fue encontrado");
            data.put("nombreCliente", c.getNombreCompleto());
            data.put("distritoCliente", c.getNombreDistrito());
            data.put("direccionCliente", c.getDireccion());
            data.put("tipo", "success");
            request.getSession().setAttribute("ClienteCompra", c);
        } else {
            data.put("ok", false);
            data.put("titulo", "No existe");
            data.put("mensaje", "No se encontro cliente con DNI " + dni);
            data.put("tipo", "error");
        }

        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

    private void procesarCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entrando a Procesar Compra");
        EmpleadoDTO em = (EmpleadoDTO) request.getSession().getAttribute("e");
        ClienteDTO cl = (ClienteDTO) request.getSession().getAttribute("ClienteCompra");
        ClienteDTO descuento = (ClienteDTO) request.getSession().getAttribute("ClienteDescuento");

        int cantidadProductos = (int) request.getSession().getAttribute("cantidadProductos");
        double subTotalVentas = (double) request.getSession().getAttribute("subTotalVentas");
        @SuppressWarnings("unchecked")
        ArrayList<DetallePedidoDTO> carro = (ArrayList<DetallePedidoDTO>) request.getSession().getAttribute("carro");
        PedidoDTO pe = new PedidoDTO();
        pe.setId_pe(generarNumPedido());
        pe.setId_em(em.getId());
        pe.setId_cli(cl.getCodigo());
        pe.setCantidadTotal(cantidadProductos);

        BoletaDTO bo = new BoletaDTO();
        bo.setId_bol(generarNumBoleta());
        bo.setPrecioTotal((subTotalVentas - (subTotalVentas * descuento.getDescuento())));
        bo.setDescuento(descuento.getDescuento());

        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        int rs = fabrica.getVentaDao().realizarVenta(pe, carro, bo);

        Map<String, Object> data = new LinkedHashMap<String, Object>();

        if (rs == 0) {
            System.out.println("Error en la transaccion");
            data.put("ok", false);
            data.put("titulo", "Error");
            data.put("mensaje", "No se pudo confirmar la compra");
            data.put("tipo", "error");
        } else {
            System.out.println("Transaccion Exitosa");
            // Reiniciar Variable Globales a nivel de Session
            request.getSession().setAttribute("ClienteCompra", new ClienteDTO());
            request.getSession().setAttribute("ClienteDescuento", new ClienteDTO());
            request.getSession().setAttribute("carro", new ArrayList<DetallePedidoDTO>());
            request.getSession().setAttribute("cantidadProductos", 0);
            request.getSession().setAttribute("subTotalVentas", 0.00);
            // Envia datos
            data.put("ok", true);
            data.put("titulo", "Realizado");
            data.put("mensaje", "Venta concretada exitosamente");
            data.put("tipo", "success");
        }

        String json = new Gson().toJson(data);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private int generarNumPedido() {
        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        return fabrica.getVentaDao().generarNumPedido();
    }

    private String generarNumBoleta() {
        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        return fabrica.getVentaDao().generaNumBoleta();
    }

    private void eliminarCompraCarro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        @SuppressWarnings("unchecked")
        ArrayList<DetallePedidoDTO> carro = (ArrayList<DetallePedidoDTO>) request.getSession().getAttribute("carro");
        int cantidadProductos = (int) request.getSession().getAttribute("cantidadProductos");
        double subTotalVentas = (double) request.getSession().getAttribute("subTotalVentas");

        int idprod = Integer.parseInt(request.getParameter("txtIdProdCarrito"));
        for (DetallePedidoDTO d : carro) {
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

    }

    private void agregarCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Ingreso al AgregarCompra");

        int cantidad = Integer.parseInt(request.getParameter("txtCantComprarCarrito"));
        System.out.println(cantidad);

        int idProducto = Integer.parseInt(request.getParameter("txtIdProdCarrito"));
        System.out.println(idProducto);

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ProductoDTO p = factory.getProductoDAO().buscar(idProducto);

        // Traer atributos de session
        @SuppressWarnings("unchecked")
        ArrayList<DetallePedidoDTO> carro = (ArrayList<DetallePedidoDTO>) request.getSession().getAttribute("carro");
        int cantidadProductos = (int) request.getSession().getAttribute("cantidadProductos");
        double subTotalVentas = (double) request.getSession().getAttribute("subTotalVentas");

        // Agregamos al carro y actualizamos los acumuladores
        DetallePedidoDTO detalle = new DetallePedidoDTO();
        detalle.setId_pro(p.getIdProducto());
        detalle.setCantidad(cantidad);
        detalle.setPrecio(p.getPrecio());

        // Adicionales
        detalle.setImagen(p.getImagen());
        detalle.setNombreProd(p.getDescripcion());
        detalle.setImporte(cantidad * p.getPrecio());

        if (carro.size() == 0) {
            carro.add(detalle);
            cantidadProductos += cantidad;
            subTotalVentas += detalle.getImporte();
        } else {
            boolean agregar = true;

            for (DetallePedidoDTO de : carro) {
                if (p.getIdProducto() == de.getId_pro()) {

                    de.setCantidad(cantidad + de.getCantidad());
                    de.setImporte((cantidad * de.getPrecio()) + de.getImporte());
                    agregar = false;
                    break;
                }
            }

            if (agregar) {
                carro.add(detalle);
            }

            cantidadProductos += cantidad;
            subTotalVentas += detalle.getImporte();

        }

        System.out.println(carro.toString());

        System.out.println("CantidadProductosTotales : " + cantidadProductos);
        System.out.println("SubtotalProductos : " + subTotalVentas);

        // Envia las variables globales a nivel de session
        request.getSession().setAttribute("carro", carro);
        request.getSession().setAttribute("cantidadProductos", cantidadProductos);
        request.getSession().setAttribute("subTotalVentas", subTotalVentas);

    }

}
