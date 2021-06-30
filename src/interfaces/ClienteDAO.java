package interfaces;

import java.util.ArrayList;

import beans.ClienteDTO;
import beans.ReporteClienteDTO;

public interface ClienteDAO {

	public ArrayList<ClienteDTO> listarCliente();
	public int registrarCliente(ClienteDTO cli);
	public int actualizarCliente(ClienteDTO cli);
	public ClienteDTO buscarCliente(int cod);
	public ArrayList<ClienteDTO> listarClientexDistrito();
	public int eliminarCliente(ClienteDTO c);
	public ArrayList<ReporteClienteDTO> reporteCliente();
	public ClienteDTO descuento(int codigo);
	public ClienteDTO buscarClienteDNI(String dni);
	public ClienteDTO ListarClienteEstado(int estado);
	
}
