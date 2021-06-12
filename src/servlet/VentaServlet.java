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
import interfaces.ClienteDAO;
import interfaces.ProductoDAO;

@MultipartConfig
@WebServlet(name = "venser", urlPatterns = { "/venser" })
public class VentaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entró al Servlet de Venta");
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
            case "procCompra":
                procesarCompra(request, response);
                break;
            case "buscarCliente":
                buscarCliente(request, response);
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

    private void buscarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String dni = request.getParameter("txtDNICli");

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ClienteDTO c = factory.getClienteDAO().buscarClienteDNI(dni);
        
        Map<String, Object> data = new LinkedHashMap<String, Object>();

        
        if (c != null) {
            data.put("ok", true);
            data.put("titulo", "Cliente Encontrado");
            data.put("mensaje", "El cliente con DNI " + dni + " fue encontrado" );
            data.put("nombreCliente", c.getNombreCompleto());
            data.put("distritoCliente", c.getNombreDistrito());
            data.put("direccionCliente", c.getDireccion());
            data.put("tipo", "success");
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
        ClienteDTO cl = (ClienteDTO) request.getSession().getAttribute("clienteEncontrado");
        int cantidadProductos = (int) request.getSession().getAttribute("cantidadProductos");
        double subTotalVentas = (double) request.getSession().getAttribute("subTotalVentas");
        ArrayList<DetallePedidoDTO> carro = (ArrayList<DetallePedidoDTO>) request.getSession().getAttribute("carro");
        PedidoDTO pe = new PedidoDTO();
        pe.setId_pe(generarNumPedido());
        pe.setId_em(em.getId());
        pe.setId_cli(cl.getCodigo());
        pe.setCantidadTotal(cantidadProductos);

        BoletaDTO bo = new BoletaDTO();
        bo.setId_bol(generarNumBoleta());
        bo.setPrecioTotal(subTotalVentas);
        
        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        int rs = fabrica.getVentaDao().realizarVenta(pe, carro, bo);
        if (rs == 0) {
            System.out.println("Error en la transaccion");
        } else {
            System.out.println("Transaccion Exitosa");
            // Reiniciar Variable Globales a nivel de Session
            request.getSession().setAttribute("carro", new ArrayList<DetallePedidoDTO>());
            request.getSession().setAttribute("cantidadProductos", 0);
            request.getSession().setAttribute("subTotalVentas", 0.00);
        }

        request.getRequestDispatcher("home.jsp").forward(request, response);
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

        listadoClienteyProducto(request, response);

    }

    private void cargarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ProductoDTO p = factory.getProductoDAO().buscar(codigo);
        request.getSession().setAttribute("productoEncontrado", p);

        listadoClienteyProducto(request, response);

    }

    private void agregarCompra(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        
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
                    de.setImporte((cantidad * de.getPrecio())+ de.getImporte());
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

    private void listadoClienteyProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Ingreso al proceso ListarClienteProducto");

        DAOFactory fabrica = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ClienteDAO dao = fabrica.getClienteDAO();
        ProductoDAO dao1 = fabrica.getProductoDAO();
        ArrayList<ClienteDTO> lista = dao.listarClientexDistrito();
        ArrayList<ProductoDTO> lista1 = dao1.listado();

        request.setAttribute("lstClientes", lista);
        request.setAttribute("lstProductos", lista1);
        request.getRequestDispatcher("catalogo.jsp").forward(request, response);

    }

    private void cargarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Ingreso al proceso SeleccionarCliente");
        int codigo = Integer.parseInt(request.getParameter("codigo"));

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        ClienteDTO c = factory.getClienteDAO().buscarCliente(codigo);
        ClienteDTO des = factory.getClienteDAO().descuento(codigo);
        request.getSession().setAttribute("clienteEncontrado", c);
        request.getSession().setAttribute("ClienteDescuento", des);

        listadoClienteyProducto(request, response);
    }

}
