package servlet;

import java.util.ArrayList;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import beans.DetallePedidoDTO;

/**
 * Application Lifecycle Listener implementation class VentaListener
 *
 */
@WebListener
public class VentaListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public VentaListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
        System.out.println("----------------------------");
        System.out.println("Crea sesion y Empezando a escuchar la sesion");
        System.out.println("ID Session: " + arg0.getSession().getId());
        System.out.println("----------------------------");
        //Variables Globales
        ArrayList<DetallePedidoDTO> carro = new ArrayList<DetallePedidoDTO>();
        int cantidadProductos = 0;
        double subTotalVentas = 0.0;
        
        arg0.getSession().setAttribute("carro", carro);
        arg0.getSession().setAttribute("cantidadProductos", cantidadProductos);
        arg0.getSession().setAttribute("subTotalVentas", subTotalVentas);
        System.out.println("Enviando variables a nivel de session");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
        System.out.println("----------------------------");
        System.out.println("Elimanando sesion y dejando de escuchar");
        System.out.println("ID Session: " + arg0.getSession().getId());
        System.out.println("----------------------------");
    }
	
}
