package interfaces;

import java.util.ArrayList;

import beans.BoletaDTO;
import beans.DetallePedidoDTO;
import beans.PedidoDTO;

public interface VentaDAO {
    public String generaNumBoleta();
    public int generarNumPedido();
    public int realizarVenta(PedidoDTO p, ArrayList<DetallePedidoDTO> det, BoletaDTO b);
    public int actualizarPedido(PedidoDTO p);
}
