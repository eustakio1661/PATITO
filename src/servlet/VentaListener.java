package servlet;

import java.util.ArrayList;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import beans.DetallePedidoDTO;

@WebListener
public class VentaListener implements HttpSessionListener {

    public VentaListener() {

    }

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

    public void sessionDestroyed(HttpSessionEvent arg0)  { 
        System.out.println("----------------------------");
        System.out.println("Eliminando sesion y dejando de escuchar");
        System.out.println("ID Session: " + arg0.getSession().getId());
        System.out.println("----------------------------");
    }
	
}
